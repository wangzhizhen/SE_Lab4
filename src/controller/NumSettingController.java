package controller;

import model.Dictionary;



public class NumSettingController {
    
	private int num;
	private int maxNum;
	private static NumSettingController instance = new NumSettingController();
	
	private NumSettingController(){
		
	}
	
	public static NumSettingController getInstance(){
		return instance;
	}
	
	public int setNum(int num){
//  check if exceed the maxNum
// if exceed, return 1; else return 0;
		if(num<=maxNum){
			this.num = num;
			return 0;
		}else{
			this.num = maxNum;
			return 1;
		}
	}
	
	public int getNum(){
		return num;		
	}
	
	public int getMaxNum(int letterPosition, int firstWordIndex){	
		int length = Dictionary.getInstance().getWordListLengthAt(letterPosition);
		maxNum = length-firstWordIndex;
		return maxNum;	
	} 
	
}
