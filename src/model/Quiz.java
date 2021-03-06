package model;

import java.util.ArrayList;

public class Quiz {
	private ArrayList<Word> quizList;
	private int totalNum;
	private int correctNum;
	private int wrongNum;
	
	public Quiz(ArrayList<Word> quizList){
		this.quizList = quizList;
	}
	
	public ArrayList<Word> getQuizList(){
		return quizList;
	}
	
	public int getCorrectNum(){
		return correctNum;
	}
	
	public void setCorrectNum(int cNum){
		this.correctNum = cNum;
	}
	
	public int getWrongNum(){
		return wrongNum;
	}
	
	public void setWrongNum(int wNum){
		this.wrongNum = wNum;
	}
	
	public int getTotalNum(){
		return totalNum;
	}
	
	public void setTotalNum(int tNum){
		this.totalNum = tNum;
	}
	
	public Word getWordAt(int currentWordIndex){
		if(currentWordIndex < 0 || currentWordIndex > quizList.size() - 1)
			return null;
		else{
			return quizList.get(currentWordIndex);
		}
	}
}
