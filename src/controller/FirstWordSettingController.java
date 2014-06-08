package controller;

import java.util.TreeSet;

import model.Dictionary;
import model.TxtFileParser;

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
		// check it userinput is a valid word in dictionary
		bool = Dictionary.getInstance().contains(letterPosition, input);

		if (bool) {
			// set the firstWordIndex using HashTable in
			// TxtFileParser()
			firstWordIndex = TxtFileParser.getInstance().getIndexHash()
					.get(input);
			return 0;
		} else {
			firstWordIndex = 0;
			return 1;
		}

	}

	public int setFromLastTime(int letterPosition) {
		int lastTimeIndex = TxtFileParser.getInstance().readLastTimeIndexFile(
				letterPosition);
		if (lastTimeIndex == Dictionary.getInstance().getWordListLengthAt(
				letterPosition)) {
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
		
		//if input is "" or null
		if(input == null || input.equals("")){
			matchResult[0] = "";
			matchResult[1] = "";
			matchResult[2] = "";
			return matchResult;
		}
		
		String lowerInput = input.toLowerCase();
		
		if ((lowerInput.charAt(0) - 'a') != letterPosition) {
			matchResult[0] = "";
			matchResult[1] = "";
			matchResult[2] = "";
		} else {
			TreeSet<String> dictionaryTree = Dictionary.getInstance()
					.getDictionaryTree();
			// get three word closest to the input
			matchResult[0] = dictionaryTree.ceiling(input);
			matchResult[1] = dictionaryTree.higher(matchResult[0]);
			matchResult[2] = dictionaryTree.higher(matchResult[1]);
			if((matchResult[0].charAt(0)-'a')!=letterPosition){
				matchResult[0] = "";
			}
			if((matchResult[1].charAt(0)-'a')!=letterPosition){
				matchResult[1] = "";
			}
			if((matchResult[2].charAt(0)-'a')!=letterPosition){
				matchResult[2] = "";
			}
			
			// Iterator it =dictionaryTree.iterator();
		}
		return matchResult;
	}
	
	public int getFirstWordIndex() {
		return firstWordIndex;
	}

}
