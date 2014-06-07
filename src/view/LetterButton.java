package view;

import javax.swing.*;

public class LetterButton extends JLabel {
	private int letterPosition = -1;
	
	public LetterButton(ImageIcon icon){
		super(icon);
	}
	
	public String toString(){
		return new Integer(letterPosition).toString();
	}
	
	public void setLetterPosition(int i){
		this.letterPosition = i;
	}
	
	
}
