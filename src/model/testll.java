package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.TreeSet;

public class testll {
	public static void main(String[] args){
		Word word2 = new Word("apple","n.Æ»¹û",0);
		ArrayList<Word> wordArray2 = new ArrayList<Word>();
		wordArray2.add(word2);
		WordList wordList2 = new WordList(0,wordArray2);
		//Case 2--letterPosition < 0
		
            Dictionary.getInstance().setWordListAt(-1, wordList2);

       
	}
	
	public static boolean dicEquals(WordList[] wordListArray, Dictionary dictionary){
		WordList[] wordListTested = dictionary.getWordListArray();
		int len = wordListArray.length;
		//Compare their length first
		if(wordListTested.length != len)
			return false;
		for(int i = 0; i < len; i ++){
			ArrayList<Word> wordArrayExpected = wordListArray[i].getWordArray();
			ArrayList<Word> wordArrayTested = wordListTested[i].getWordArray();
			//Compare each wordArray's size first
			if(wordArrayExpected.size() != wordArrayTested.size())
				return false;
			int size = wordArrayExpected.size();
			for(int j = 0; j < size; j ++){
				Word wordExpected = wordArrayExpected.get(j);
				Word wordTested = wordArrayTested.get(j);
				if(!(wordExpected.getChinese().equals(wordTested.getChinese())&&
						wordExpected.getEnglish().equals(wordTested.getEnglish())&&
						wordExpected.getState()==wordTested.getState()))
					return false;
			}
		}
		return true;
	}
}
