package controller;

import java.util.TreeSet;

import model.DictionaryForXml;
import model.XmlFileParser;

public class FirstWordSettingController {
	private int firstWordIndex;
	// implement as singleton
	private static FirstWordSettingController instance = new FirstWordSettingController();

	private FirstWordSettingController() {

	}

	public static FirstWordSettingController getInstance() {
		return instance;
	}

	public int setFromFirstWord() {
		firstWordIndex = 0;
		return 0;
	}

	public int setFromUserInput(int letterPosition, String input) {
		boolean bool = true;
		// check if the input is in the DictionaryForXml
		bool = DictionaryForXml.getInstance().contains(letterPosition, input);

		if (bool) {
			// set the firstWordIndex using HashTable in
			// XmlFileParser()
			firstWordIndex = XmlFileParser.getInstance()
					.getIndexHashAt(letterPosition).get(input);
			System.out
					.println("firstWordIndex from FirstWordSettingController:"
							+ firstWordIndex);
			System.out.println("input word from FirstWordSettingController:"
					+ input);
			return 0;
		} else {
			firstWordIndex = 0;
			return 1;
		}

	}

	public int setFromLastTime(int letterPosition) {
		int lastTimeIndex = XmlFileParser.getInstance().readLastTimeIndexFile(
				letterPosition, "file/LastTimeIndexFile.txt");
		System.out.println(DictionaryForXml.getInstance().getWordListLengthAt(
				letterPosition));
		if (lastTimeIndex == DictionaryForXml.getInstance()
				.getWordListLengthAt(letterPosition)) {

			firstWordIndex = 0;
			return 2;
		} else if (lastTimeIndex == -1) {
			// firstWordIndex = 0;//
			return -1;
		} else {
			firstWordIndex = lastTimeIndex;
			return 0;
		}

	}

	public String[] stringMatching(String input, int letterPosition) {
		// use TreeSet to do the string matching
		// get the word

		String[] matchResult = new String[3];

		// if input is "" or null
		if (input == null || input.equals("")) {
			matchResult[0] = "";
			matchResult[1] = "";
			matchResult[2] = "";
			return matchResult;
		}
//		String lowerInput = input.toLowerCase();

	
			TreeSet<String> DictionaryForXmlTree = DictionaryForXml
					.getInstance().getDictionaryTreeAt(letterPosition);
			// get three word closest to the input
			matchResult[0] = DictionaryForXmlTree.ceiling(input);
			if(matchResult[0] != null){
				matchResult[1] = DictionaryForXmlTree.higher(matchResult[0]);
				if(matchResult[1] != null){
					matchResult[2] = DictionaryForXmlTree.higher(matchResult[1]);
					if(matchResult[2]==null)
						matchResult[2]="";
				}else {
					matchResult[1] = "";
					matchResult[2] = "";
				}
			}else{
				matchResult[0] = "";
				matchResult[1] = "";
				matchResult[2] = "";
			}

			for(int i = 0;i<matchResult.length;i++){
				if(matchResult[i].startsWith(input) == false){
					matchResult[i] = "";
				}
			}

			// Iterator it =DictionaryForXmlTree.iterator();
		
		return matchResult;
	}

	public int getFirstWordIndex() {
		return firstWordIndex;
	}

}
