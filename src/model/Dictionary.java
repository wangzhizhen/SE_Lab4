package model;

import java.util.ArrayList;
import java.util.TreeSet;

public class Dictionary implements IDictionary{
	WordList[] wordListArray = new WordList[26];
	TreeSet<String> dictionaryTree = new TreeSet<String>();
	
	private Dictionary(){
		
	}
	
	private static final Dictionary dictionary = new Dictionary();
	
	public static Dictionary getInstance() {
        return dictionary;
    }
	
	public WordList[] getWordListArray(){
		return wordListArray;
	}
	
	public void setWordListArray(WordList[] wordListArray){
		this.wordListArray = wordListArray;
	}
	
	public void setWordListAt(int letterPosition,WordList wordList){
		try{
			if(letterPosition >= 0 && letterPosition < 26)
				wordListArray[letterPosition] = wordList;
		}
		catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
	
	public boolean contains(int letterPosition, String input){
		if(letterPosition < 0 || letterPosition > wordListArray.length - 1)
			return false;
		else{
			ArrayList<Word> wordList = wordListArray[letterPosition].getWordArray();
			int len = wordList.size();
			for(int i = 0; i < len; i ++){
				String English = wordList.get(i).getEnglish();
				if(English.equals(input)){
					return true;
				}
			}
		}
		return false;
	}
	
	public TreeSet<String> getDictionaryTree(){
		return dictionaryTree;
	}
	
	public void setDictionaryTree(TreeSet<String> dictionaryTree){
		this.dictionaryTree = dictionaryTree;
	}
	
	public int getWordListLengthAt(int letterPosition){
		if(letterPosition < 0 || letterPosition > wordListArray.length - 1)
			return -1;
		else
			return wordListArray[letterPosition].getWordArray().size();
	}
	

}
