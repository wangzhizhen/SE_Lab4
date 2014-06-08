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
		correctNum = cNum ;
	}
	
	public int getWrongNum(){
		return wrongNum;
	}
	
	public void setWrongNum(int wNum){
		wrongNum = wNum;
	}
	
	public int getTotalNum(){
		return totalNum;
	}
	
	public void setTotalNum(int tNum){
		totalNum = tNum;
	}
	
	public Word getWordAt(int currentWordIndex){
			System.out.println();
			return quizList.get(currentWordIndex);
		
	}
}
