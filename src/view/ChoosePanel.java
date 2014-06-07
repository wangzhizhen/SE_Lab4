package view;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.*;

public class ChoosePanel extends JPanel{
	private ArrayList<LetterButton> letters =new ArrayList<LetterButton>(); 
	private int letterPosition = -1; // record the chosen letter(word bank)
	private JLabel startButton = new JLabel();
	private JLabel resultButton = new JLabel();
	private JLabel allResultButton = new JLabel();
	
	public ChoosePanel(){
		//initialize the letter labels list
		int i = 0;
		for(i =0; i < 26; i++){
			char c = (char) ('A' + i);
			LetterButton letterLabel =new LetterButton(new ImageIcon("icons/"+c+".png"));
			letterLabel.setLetterPosition(i);
			letters.add(letterLabel);
		}
		
		//draw the bord
		BorderLayout borderLayout  = new BorderLayout();
		setLayout(borderLayout);
	
		//north, east, west labels are used for padding
		//letterPanel holds the letters, buttonPanel holds the buttons
		JLabel northPanel = new JLabel();
		northPanel.setOpaque(false);
		northPanel.setPreferredSize(new Dimension(0,100));
		add(northPanel, BorderLayout.NORTH);
		
		JPanel letterPanel = new JPanel();
		letterPanel.setOpaque(false);
		letterPanel.setPreferredSize(new Dimension(678,400));
		add(letterPanel, BorderLayout.CENTER);
		
		JLabel westPanel = new JLabel();
		westPanel.setOpaque(false);
		westPanel.setPreferredSize(new Dimension(100,380));
		add(westPanel, BorderLayout.WEST);
		
		JLabel eastPanel = new JLabel();
		eastPanel.setOpaque(false);
		eastPanel.setPreferredSize(new Dimension(100,380));
		add(eastPanel, BorderLayout.EAST);
	
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setPreferredSize(new Dimension(0,100));
		add(buttonPanel, BorderLayout.SOUTH);
		
		//add letters icons to centerPanel
		letterPanel.setLayout(new GridLayout(4,7));
		int j = 0;
		for(j = 0; j < 26; j++){
			//put each letter into a oneLetterPanel, which contains the letterLabel and four paddings,  in order to leave some space between letters
			JPanel oneLetterPanel = new JPanel();
			oneLetterPanel.setLayout(new BorderLayout());
			oneLetterPanel.setOpaque(false);
			
			//add the letter label into the oneLetterPanel
			oneLetterPanel.add(letters.get(j), BorderLayout.CENTER);
			
			JLabel padding1 = new JLabel();
			padding1.setOpaque(false);
			padding1.setPreferredSize(new Dimension(0,15));
			oneLetterPanel.add(padding1, BorderLayout.NORTH);
			
			JLabel padding2 = new JLabel();
			padding2.setOpaque(false);
			padding2.setPreferredSize(new Dimension(0,15));
			oneLetterPanel.add(padding2, BorderLayout.SOUTH);
			
			JLabel padding3 = new JLabel();
			padding3.setOpaque(false);
			padding3.setPreferredSize(new Dimension(15,0));
			oneLetterPanel.add(padding3, BorderLayout.EAST);
			
			JLabel padding4 = new JLabel();
			padding4.setOpaque(false);
			padding4.setPreferredSize(new Dimension(15,0));
			oneLetterPanel.add(padding4, BorderLayout.WEST);
			
			letterPanel.add(oneLetterPanel);
		}
		
		//add buttons to southPanel
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
		
		startButton.setIcon(new ImageIcon("icons/startButton.png"));
		buttonPanel.add(startButton);
		resultButton.setIcon(new ImageIcon("icons/resultButton.png"));
		buttonPanel.add(resultButton);
		allResultButton.setIcon(new ImageIcon("icons/allResultButton.png"));
		buttonPanel.add(allResultButton);
		
//		initActsForInsideButton();
	}
	
	public JLabel getStartButton(){
		return this.startButton;
	}
	
	public JLabel getResultButton(){
		return this.resultButton;
	}
	
	public JLabel getAllResultButton(){
		return this.allResultButton;
	}
	
	public int getLetterPosition(){
		return this.letterPosition;
	}
	
	public void setLetterPostion(int position){
		this.letterPosition = position;
		
		int j = 0;
		for(j = 0; j<26; j++){
			char c = (char) ( 'A' + j);
			if(j!=letterPosition)
				letters.get(j).setIcon(new ImageIcon("icons/"+c+".png"));
			else
				letters.get(j).setIcon(new ImageIcon("icons/"+c+"_1.png"));
		}
		
		repaint();
	}
	
	public ArrayList<LetterButton> getLetters(){
		return this.letters;
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		//set the background image 
		Image bgImg = new ImageIcon("icons/bgChoosePanel.png").getImage();
		g.drawImage(bgImg, 0, 0, getSize().width, getSize().height, this);
		
		
	}
	
//	private void initActsForInsideButton(){
//		
//	}
	
	
}
