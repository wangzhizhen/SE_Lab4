package controller;



public class LetterPositionSettingController {
	private int letterPosition;
	// implement as singleton
	private static LetterPositionSettingController instance = new LetterPositionSettingController();
		
	private LetterPositionSettingController() {

	}
	public static LetterPositionSettingController getInstance(){
		return instance;
	}
	public int getLetterPosition(){
		return this.letterPosition;
	}
	
	public void setLetterPosition(int letterPosition){
		this.letterPosition = letterPosition;
		
	}
	
	

}
