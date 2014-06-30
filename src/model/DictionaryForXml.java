package model;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * New implementation for IDictionary
 * */
public class DictionaryForXml implements IDictionary {
	//TODO: still do not know how many word-lists are there in this version
	//This point out the number of word-lists this version
	private final int WORDLIST_COUNT=10;

	//There are __ word-lists in the dictionary for version 2
	private WordList[] wordListArray = new WordList[WORDLIST_COUNT];
	
	//Different tree-set for different word-list
	private ArrayList<TreeSet<String>> dictionaryTreeArrayList = new ArrayList<TreeSet<String>>();
	
	//Implement the class using singleton pattern
	private static final DictionaryForXml dictionaryForXML = new DictionaryForXml();
	
	private DictionaryForXml() {
	}
	
	//Singleton pattern
	public static DictionaryForXml getInstance() {
		return dictionaryForXML;
	}
	
	@Override
	public WordList[] getWordListArray() {
		return wordListArray;
	}
	
	public void setWordListArray(WordList[] wordListArray){
		this.wordListArray = wordListArray;
	}
	
	public Word getWordAt(int wordListPosition, int wordIndex){
		return wordListArray[wordListPosition].getWordArray().get(wordIndex);
	}
	
	public void setWordListAt(int wordListPosition,WordList wordList){
		try{
			if(wordListPosition >= 0 && wordListPosition < WORDLIST_COUNT)
				wordListArray[wordListPosition] = wordList;
		}
		catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * get the tree set for the certain word-list position 
	 * */
	public TreeSet<String> getDictionaryTreeAt(int wordListPosisitoin){
		return this.dictionaryTreeArrayList.get(wordListPosisitoin);
	}
	
	public void setDictionaryTree(ArrayList<TreeSet<String>> dictionaryTreeArray){
		this.dictionaryTreeArrayList = dictionaryTreeArray;
	}
	
	public int getWordListLengthAt(int wordListPosition){
		if(wordListPosition < 0 || wordListPosition > wordListArray.length - 1)
			return -1;
		else
			return wordListArray[wordListPosition].getWordArray().size();
	}
	
	public boolean contains(int wordPosition, String input){
		if(wordPosition < 0 || wordPosition > wordListArray.length - 1)
			return false;
		else{
			
			ArrayList<Word> wordList = wordListArray[wordPosition].getWordArray();
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
	
}
