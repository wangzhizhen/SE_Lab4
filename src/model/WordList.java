package model;

import java.util.ArrayList;

public class WordList {
	int lastTimeIndex;
	ArrayList<Word> wordArray;
	
	public WordList(){
		
	}
	
	@SuppressWarnings("unchecked")
	public WordList(int lastTimeIndex, ArrayList<Word> wordArray){
		this.lastTimeIndex = lastTimeIndex;
		this.wordArray = (ArrayList<Word>) wordArray.clone();
	}
	
	public int getLastTimeIndex(){
		return lastTimeIndex;
	}
	
	public void setLastTimeIndex(int lastTimeIndex){
		this.lastTimeIndex = lastTimeIndex;
	}
	
	public void setWordAt(int index,Word word){
		wordArray.set(index, word);
	}
	
	public ArrayList<Word> getWordArray(){
		return wordArray;
	}
	
	public void setWordArray(ArrayList<Word> wordArray){
		this.wordArray = wordArray;
	}
}
