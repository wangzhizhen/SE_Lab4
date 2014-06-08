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
//		�ж�num�Ƿ�Ϸ����������0��С�ڴʿ�ʣ�������
//		���С��0��������ֵ�򷵻ش�����ʾ���������ʣ������򷵻���ʾ��Ĭ������Ϊʣ�����		
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
