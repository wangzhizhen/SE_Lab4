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
//		判断num是否合法，必须大于0且小于词库剩余词数；
//		如果小于0或是其他值则返回错误提示；如果大于剩余词数则返回提示，默认设置为剩余词数		
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
