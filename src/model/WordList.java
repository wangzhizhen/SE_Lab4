package model;

import java.util.ArrayList;

public class WordList {
	private int lastTimeIndex;
	private ArrayList<Word> wordArray;
	
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
		if(index >= 0 && index < wordArray.size())
			wordArray.set(index, word);
	}
	
	public ArrayList<Word> getWordArray(){
		return wordArray;
	}
}
