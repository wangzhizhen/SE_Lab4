package model;

public class Word {
	private String english;
	private String chinese;
	private int state;//0-not yet;1-wrong;2-correct
	
	public Word(String english,String chinese,int state){
		this.english = english;
		this.chinese = chinese;
		this.state = state;
	}
	
	public String getEnglish(){
		return english;
	}
	
	public String getChinese(){
		return chinese;
	}
	
	public int getState(){
		return state;
	}
	
	public void setState(int state){
		this.state = state;
	}
}

