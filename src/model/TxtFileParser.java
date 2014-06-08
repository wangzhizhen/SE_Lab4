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

public class TxtFileParser implements IDictionaryParser {
	private Hashtable<String, Integer> indexHash = new Hashtable<String, Integer>();

	private TxtFileParser() {
	}

	private static final TxtFileParser instance = new TxtFileParser(
			);

	public static TxtFileParser getInstance() {
		return instance;
	}

	public void init(String filename) {
		int num = readFromTxtFile(filename);
		initTheStateFile(num,"file/statefile.txt");

	}

	@Override
	public IDictionary getDictionary() {
		return Dictionary.getInstance();
	}

	@Override
	public void setDictionary(IDictionary dictionary) {

	}

	public int readFromTxtFile(String dicfilename) {
		char[] firstCharArr = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z' };
		int index = 0;
		int hashIndex = 0;
		int num = 0;
		TreeSet<String> dictionaryTree = new TreeSet<String>();
		String[] wordArr = new String[2];
		ArrayList<Word> wordList = new ArrayList<Word>();
		WordList[] IDictionary = new WordList[26];
		try {
			String line;
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(dicfilename),
							"GBK"));
			// Read content from the txtfile line by line
			while ((line = bufferedReader.readLine()) != null) {
				// Separate the word and translation
				wordArr = line.split("   ");
				// New a word
				Word word = new Word(wordArr[0], wordArr[1], 0);
				dictionaryTree.add(wordArr[0]);
				num++;
				// Put the word and its index into the indexHash
				if (wordArr[0].charAt(0) == firstCharArr[index]) {
					wordList.add(word);
					indexHash.put(wordArr[0], hashIndex);
					hashIndex++;
				} else {
					WordList wl = new WordList(
							readLastTimeIndexFile(index + 1), wordList);
					IDictionary[index] = wl;
					wordList.clear();
					wordList.add(word);
					hashIndex = 0;
					indexHash.put(wordArr[0], hashIndex);
					hashIndex ++;
					index++;
				}
			}
			WordList wl = new WordList(readLastTimeIndexFile(index + 1),
					wordList);
			IDictionary[index] = wl;
			bufferedReader.close();
			Dictionary dictionary = Dictionary.getInstance();
			dictionary.setWordListArray(IDictionary);
			dictionary.setDictionaryTree(dictionaryTree);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return num;
	}

	public void initTheStateFile(int num,String filename) {
		File file = new File(filename);

		try {
			if (!file.exists()) {
				file.createNewFile();
				// Construct the BufferedWriter object
				BufferedWriter bufferedWriter = new BufferedWriter(
						new FileWriter(filename));
				// Start writing to the output stream
				for (int i = 0; i < num; i++) {
					bufferedWriter.write("0");
					bufferedWriter.newLine();
				}
				// Close the output stream
				bufferedWriter.close();
			} 
//			 {
				BufferedReader bufferedReader = new BufferedReader(
						new FileReader(filename));
				int state = 0;
				for (int i = 0; i < 26; i++) {
					ArrayList<Word> wordArray = Dictionary.getInstance()
							.getWordListArray()[i].getWordArray();
					int len = wordArray.size();
					for (int j = 0; j < len; j++) {
						state = Integer.parseInt(bufferedReader.readLine());
						Dictionary.getInstance()
						.getWordListArray()[i].getWordArray().get(j).setState(state);
//						System.out.println(wordArray.get(0).getState());
					}

				}
				bufferedReader.close();
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int[] getStateStatistics(int letterPosition) {
		int notY = 0;
		int wrong = 0;
		int correct = 0;
		int state = 0;
		if (letterPosition >= 0 && letterPosition < 26) {
			WordList wordList = Dictionary.getInstance().getWordListArray()[letterPosition];
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

		} else if (letterPosition == 26) {
			for (int i = 0; i < 26; i++) {
				WordList wordList = Dictionary.getInstance().getWordListArray()[i];
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

	public void updateTheState(Quiz quiz) {
		ArrayList<Word> quizList = quiz.getQuizList();
		int size = quizList.size();
		char[] firstCharArr = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z' };
		char firstChar = quizList.get(0).getEnglish().charAt(0);

		// Modify dictionary according to quiz
		for (int i = 0; i < 26; i++) {
			if (firstChar == firstCharArr[i]) {
				ArrayList<Word> tmpList = Dictionary.getInstance()
						.getWordListArray()[i].getWordArray();
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
					int oldState = Dictionary.getInstance().getWordListArray()[i].getWordArray().get(j).getState();
					System.out.println(oldState);
					int newState = quizList.get(tmp).getState();
					System.out.println(newState);
					if(oldState != 2){
					Dictionary.getInstance().getWordListArray()[i]
							.getWordArray().get(j)
							.setState(newState);
					}
					tmp++;
				}
				break;
			}
		}
	}

	public void modifyTheStateFile(String filename) {
		int state = 0;
//		String filename = "file/statefile.txt";
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
			// Rewrite the state file according to dictionary
			for (int i = 0; i < 26; i++) {
				WordList wordList = Dictionary.getInstance().getWordListArray()[i];
				ArrayList<Word> wordArray = wordList.getWordArray();
				int len = wordArray.size();
				for (int j = 0; j < len; j++) {
					state = wordArray.get(j).getState();
					bufferedWriter.write(state + "");
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Hashtable<String, Integer> getIndexHash() {
		return indexHash;
	}

	public int readLastTimeIndexFile(int letterPosition) {
		int index = 0;
		String lastTimeIndexFile = "file/LastTimeIndexFile.txt";
		// File file = new File(lastTimeIndexFile);
		try {
			if (letterPosition < 26) {
				BufferedReader bufferedReader = new BufferedReader(
						new FileReader(lastTimeIndexFile));
				for (int i = 0; i < letterPosition; i++) {
					bufferedReader.readLine();
				}
				index = Integer.parseInt(bufferedReader.readLine());
				bufferedReader.close();
			}

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}
		return index;
	}

	public void setLastTimeIndexFile(int letterPosition, int currentIndex) {
		String filename = "file/LastTimeIndexFile.txt";
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String index;
			String string = "";
			for (int i = 0; i < 25; i++) {
				if (i != letterPosition) {
					index = bufferedReader.readLine();
					string += index;
					string += "\r\n";
				} else {
					bufferedReader.readLine();
					string += currentIndex;
					string += "\r\n";
				}
			}
			index = bufferedReader.readLine();
			string += index;
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

	public void saveToAllFiles(Quiz quiz, int cur, String filename) {
		String letterStr = "abcdefghijklmnopqrstuvwxyz";
		int index = letterStr.indexOf(quiz.getWordAt(0).getEnglish().charAt(0));
		updateTheState(quiz);
		modifyTheStateFile(filename);
		setLastTimeIndexFile(index, cur);
	}

}
