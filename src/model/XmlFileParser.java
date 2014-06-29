package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

import javax.xml.bind.TypeConstraintException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.security.Init;



/**
 * New implementation for IDictionaryParser
 * */
public class XmlFileParser implements IDictionaryParser {
	// This point out the number of word-lists this version
	private final int WORDLIST_COUNT = 10;
	
	//define the number
	private String[] typeStrings = {"adv.","pron.","v.","adj.","n.","prep.","int.","conj.","aux.","num."};

	// Each word-list needs a hash-table to record the index of the word in the
	// word-list
	private ArrayList<Hashtable<String, Integer>> indexHash = new ArrayList<Hashtable<String, Integer>>();

	// Implement the interface as a using singleton pattern
	private static final XmlFileParser instance = new XmlFileParser();
	
	@Override
	public IDictionary getDictionary() {
		return DictionaryForXml.getInstance();
	}

	@Override
	public void setDictionary(IDictionary dictionary) {
		// TODO Auto-generated method stub

	}
	
	public static XmlFileParser getInstance() {
		return instance;
	}
	
	/**
	 * Get the hash-table for a certain word-list
	 * @param wordListPosition the index of the word-list in the new dictionary
	 * @return the hash-table of thid word-list
	 * */
	public Hashtable<String, Integer> getIndexHashAt(int wordListPosition) {
		return indexHash.get(wordListPosition);
	}
	
	/**
	 * Read the Xml file and finish some needed initialization
	 * @param dicfilename the name of the xml file which contains all of the words
	 * @return the array that holds the number of each word-list
	 * */
	public int[] readFromXmlFile(String dicfilename) {
		String[] wordArr = new String[2];

		// initialize the array list,the hash-table array-list
		ArrayList<TreeSet<String>> dictionaryTreeaArrayList = new ArrayList<TreeSet<String>>();
		int[] hashIndexArr = new int[WORDLIST_COUNT];
		WordList[] dictionaryWordLists = new WordList[WORDLIST_COUNT];

		WordList tmpWordList;
		ArrayList<Word> tmpwordaArrayList;
		for (int i = 0; i < WORDLIST_COUNT; i++) {
			tmpWordList = new WordList();
			tmpwordaArrayList = new ArrayList<Word>();
			tmpWordList.setWordArray(tmpwordaArrayList);

			dictionaryWordLists[i] = tmpWordList;
			indexHash.add(new Hashtable<String, Integer>());
			dictionaryTreeaArrayList.add(new TreeSet<String>());
			hashIndexArr[i] = 0;
		}

		try {
			// read the xml
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Document doc = db.parse(new File(dicfilename));
			Element elmtInfo = doc.getDocumentElement();
			NodeList nodes = elmtInfo.getChildNodes();
			// read the content in each <word></word>
			for (int i = 0; i < nodes.getLength(); i++) {
				Node result = nodes.item(i);
				if (result.getNodeType() == Node.ELEMENT_NODE
						&& result.getNodeName().equals("word")) {
					NodeList ns = result.getChildNodes();

					// read the English and Chinese of the word
					for (int j = 0; j < ns.getLength(); j++) {
						Node record = ns.item(j);

						if (record.getNodeType() == Node.ELEMENT_NODE
								&& record.getNodeName().equals("english")) {
							wordArr[0] = record.getTextContent();
							// System.out.println(wordArr[0]);
						} else if (record.getNodeType() == Node.ELEMENT_NODE
								&& record.getNodeName().equals("chinese")) {
							wordArr[1] = record.getTextContent();
							// System.out.println(wordArr[1]);
							Word word = new Word(wordArr[0], wordArr[1], 0);

							// put the word into the word-list accordingly
							String[] arrStrings = wordArr[1].split(",");
							for (int k = 0; k < arrStrings.length; k++) {
								System.out.println(arrStrings[k]);
								// increase the number of each word-list
								// according to their type
								for (int k2 = 1; k2 <= typeStrings.length; k2++) {
									if (arrStrings[k]
											.contains(typeStrings[k2 - 1])) {
										// add the word into the word-list
										dictionaryWordLists[k2 - 1]
												.getWordArray().add(word);

										// add the English of the word into the
										// dictionary tree
										dictionaryTreeaArrayList.get(k2 - 1)
												.add(wordArr[0]);

										// save the index of the word into index
										// table
										indexHash.get(k2 - 1).put(wordArr[0],
												hashIndexArr[k2 - 1]);

										System.out.println("wordlist:"
												+ typeStrings[k2 - 1]);
										System.out
												.println("word: "
														+ dictionaryWordLists[k2 - 1]
																.getWordArray()
																.get(hashIndexArr[k2 - 1])
																.getEnglish()
														+ "\n"
														+ dictionaryWordLists[k2 - 1]
																.getWordArray()
																.get(hashIndexArr[k2 - 1])
																.getChinese());

										// increase the number count for the
										// index of the word
										hashIndexArr[k2 - 1]++;
										break;
									}
								}
							}

						}
					}
				}

			}

			// set the last time index for each word-list from the
			// lastTimeIndexFile
			for (int i = 0; i < WORDLIST_COUNT; i++) {
				dictionaryWordLists[i].setLastTimeIndex(readLastTimeIndexFile(
						i, "file/LastTimeIndexFile.txt"));
				System.out.println(typeStrings[i]+" : "+indexHash.get(i).size());
			}

			DictionaryForXml dictionary = DictionaryForXml.getInstance();
			dictionary.setWordListArray(dictionaryWordLists);
			dictionary.setDictionaryTree(dictionaryTreeaArrayList);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return hashIndexArr;
	}

	/**
	 * Read the file which stores the index of last time quit
	 * @param wordListPosition the certain word-list expected to be queried
	 * @param lastTimeIndexFile the path of the file
	 * @return the value of the last time index
	 * */
	public int readLastTimeIndexFile(int wordListPosition,
			String lastTimeIndexFile) {
		int index = 0;
		try {
			if (wordListPosition < 10) {
				BufferedReader bufferedReader = new BufferedReader(
						new FileReader(lastTimeIndexFile));
				for (int i = 0; i < wordListPosition; i++) {
					bufferedReader.readLine();
				}
				index = Integer.parseInt(bufferedReader.readLine());
				bufferedReader.close();
			}else{
				System.out.println("Error word-list position!!!:above 10");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return index;
	}
	
	public void setLastTimeIndexFile(int wordListPosition, int currentIndex,
			String filename) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					filename));
			String index;
			String string = "";
			for (int i = 0; i < 9; i++) {
				if (i != wordListPosition) {
					index = bufferedReader.readLine();
					string += index;
					string += "\r\n";
				} else {
					bufferedReader.readLine();
					string += currentIndex;
					string += "\r\n";
				}
			}
			if (wordListPosition != 9) {
				index = bufferedReader.readLine();
				string += index;
			} else {
				string += currentIndex;
			}
			bufferedReader.close();
			File file = new File(filename);
			// Delete the old file
			if (file.exists())
				file.delete();
			// Create a new file
			if (!file.exists())
				file.createNewFile();
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
					filename));
			bufferedWriter.write(string);
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveToAllFiles(int wordListPosition,Quiz quiz, int cur, String filename) {
		updateTheState(wordListPosition,quiz);
		modifyTheStateFile(wordListPosition,filename);
		System.out.println("cur from XmlFileParser:" + cur);

		setLastTimeIndexFile(wordListPosition, cur, "file/LastTimeIndexFile.txt");
	}
	
	//test
	public static void main(String[] args) {
		String filenameString = "./file/dictionary.xml";
		XmlFileParser tmpParser = new XmlFileParser();
		tmpParser.init(filenameString);

	}
	
	/**
	 * The entry to initialize everything we need by invoking two functions
	 * @param filename the file name of the xml file which holds all of the words
	 * */
	@Override
	public void init(String filename) {
		int[] num = readFromXmlFile(filename);
		initTheStateFile(num,"file/statefile.txt");

	}
	
	public void updateTheState(int wordListPosition,Quiz quiz) {
		ArrayList<Word> quizList = quiz.getQuizList();
		int size = quizList.size();

		ArrayList<Word> tmpList = DictionaryForXml.getInstance().getWordListArray()[wordListPosition]
				.getWordArray();
		int len = tmpList.size();
		int start = 0;
		int end = 0;
		// Find the index of the start word
		for (int j = 0; j < len; j++) {
			// If this word's Chinese and English meaning are the same
			// with the start word
			if (quizList.get(0).getEnglish()
					.equals(tmpList.get(j).getEnglish())
					&& quizList.get(0).getChinese()
							.equals(tmpList.get(j).getChinese())) {
				start = j;
				break;
			}
		}
		// Find the index of the end word
		for (int j = 0; j < len; j++) {
			// If this word's Chinese and English meaning are the same
			// with the end word
			if (quizList.get(size - 1).getEnglish()
					.equals(tmpList.get(j).getEnglish())
					&& quizList.get(size - 1).getChinese()
							.equals(tmpList.get(j).getChinese())) {
				end = j;
				break;
			}
		}
		int tmp = 0;
		// Modify the states
		for (int j = start; j <= end; j++) {
			int oldState = DictionaryForXml.getInstance().getWordListArray()[wordListPosition]
					.getWordArray().get(j).getState();
			System.out.println(oldState);
			int newState = quizList.get(tmp).getState();
			System.out.println(newState);
			if (oldState != 2) {
				DictionaryForXml.getInstance().getWordListArray()[wordListPosition]
						.getWordArray().get(j).setState(newState);
			}
			tmp++;
		}
			
		
	}
	
	public void modifyTheStateFile(int wordListPostition, String filename) {
		int state = 0;
		File file = new File(filename);
		try {
			// Delete the old file
			if (file.exists())
				file.delete();
			// Create a new file
			if (!file.exists())
				file.createNewFile();
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
					filename));
			WordList wordList = DictionaryForXml.getInstance().getWordListArray()[wordListPostition];
			ArrayList<Word> wordArray = wordList.getWordArray();
			int len = wordArray.size();
			for (int j = 0; j < len; j++) {
				state = wordArray.get(j).getState();
				bufferedWriter.write(state + "");
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int[] getStateStatistics(int wordListPosition) {
		int notY = 0;
		int wrong = 0;
		int correct = 0;
		int state = 0;
		// if wordListPosition is between 0 to 9, that means user desires 
		// to see the statistics for a certain wordlist
		if (wordListPosition >= 0 && wordListPosition < 10) {
			WordList wordList = DictionaryForXml.getInstance().getWordListArray()[wordListPosition];
			ArrayList<Word> wordArray = wordList.getWordArray();
			int len = wordArray.size();
			for (int i = 0; i < len; i++) {
				state = wordArray.get(i).getState();
				if (state == 0)
					notY++;
				else if (state == 1)
					wrong++;
				else
					correct++;
			}

		} else if (wordListPosition == 10) {
			// if the wordListPosition equals to 10 , that means user desires to 
			// see the statistics of all data
			for (int i = 0; i < 10; i++) {
				WordList wordList = DictionaryForXml.getInstance().getWordListArray()[i];
				ArrayList<Word> wordArray = wordList.getWordArray();
				int len = wordArray.size();
				for (int j = 0; j < len; j++) {
					state = wordArray.get(j).getState();
					if (state == 0)
						notY++;
					else if (state == 1)
						wrong++;
					else
						correct++;
				}
			}
		}
		int[] stateArr = { notY + wrong + correct, wrong + correct, correct };
		return stateArr;
	}



	public void initTheStateFile(int[] num,String filename) {
		int max=0;
		for (int i = 0; i < num.length; i++) {
			max+=num[i];
		}
		File file = new File(filename);
		try {
			if (!file.exists()) {
				file.createNewFile();
				// Construct the BufferedWriter object
				BufferedWriter bufferedWriter = new BufferedWriter(
						new FileWriter(filename));
				// Start writing to the output stream
//				System.out.println("*******" + indexHash.size());
				int tmpSize = 0;
				for (int i = 0; i < num.length; i++) {
					bufferedWriter.write("===="+typeStrings[i]+"====");
					bufferedWriter.newLine();
//					System.out.println();
					tmpSize = num[i];
					for (int j = 0; j < tmpSize; j++) {
						bufferedWriter.write("0");
						bufferedWriter.newLine();
						
					}
				}
				// Close the output stream
				bufferedWriter.close();
			} else {
				BufferedReader bufferedReader = new BufferedReader(
						new FileReader(filename));
				int state = 0;
			int count=1;
				for (int i = 0; i < 10; i++) {
					System.out.println(i+"");
					String tmpLine = bufferedReader.readLine();
					while(!tmpLine.contains("==" + typeStrings[i])){
						if (count < max) {
							tmpLine = bufferedReader.readLine();
							count++;
							System.out.println(count);
						}else{
							System.out.println("out of bound!!!");
							break;
						}
					}
					
					ArrayList<Word> wordArray = DictionaryForXml.getInstance()
							.getWordListArray()[i].getWordArray();
					int len = wordArray.size();
					for (int j = 0; j < len; j++) {
						 state = Integer.parseInt(bufferedReader.readLine());
						 wordArray.get(j).setState(state);
						 System.out.println(state+"");
					}
				}
				bufferedReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
}
