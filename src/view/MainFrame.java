package view;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class MainFrame extends JFrame{
	private CardLayout cardLayout = new CardLayout();
	/*datas for panels*/
	private ChoosePanel choosePanel = new ChoosePanel();
	
	public MainFrame(){
//		TxtFileParser parser = TxtFileParser.getInstance();
		
		//add Panels to the MainFrame
		getContentPane().setLayout(cardLayout);
		add(choosePanel,"choosePanel");	
		
		initActsForChoosePanel();
		
		//set some basic attributes of the frame
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("WordMaster");
		setVisible(true);
		
	}
	
	public static void main(String args[]){
		MainFrame mainFrame = new MainFrame();
		
	}
	
	public void initActsForChoosePanel(){
		
		//add listener to getStartButton
		choosePanel.getStartButton().addMouseListener(new MouseAdapter(){
			//when mouse enter the letter, cursor becomes a hand 
			public void mouseEntered(MouseEvent e){
				choosePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e){
				choosePanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			//when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e){
				//if no word bank is chosen, show a message 
				if(choosePanel.getLetterPosition() < 0) {
					JOptionPane.showMessageDialog(null, "请选择词库！");
				}
				//otherwise switch the panel
				else {
					//Before switching the panel, give the selected letter, which indicates the word bank chose, to the controller
					controller.setLetterPosition(choosePanel.getLetterPosition());
					//switch panel
					wordPanel = new WordPanel();
					initActsForWordPanel();
					// if there the user hasn't studied this word list before
					int hasLastTime = controller.setFirstWordIndex(WordPanel.StartFromLast, controller.getLetterPosition(), "");
					if(hasLastTime<0){
						wordPanel.setOption(-1);
					}
					else{
						wordPanel.setOption(WordPanel.StartFromFirst);
					}
					add(wordPanel,"wordPanel");
					remove(choosePanel);
				}
			}
		});
		
		//add listeners to getResultButton
		choosePanel.getResultButton().addMouseListener(new MouseAdapter(){
			//when mouse enter the letter, cursor becomes a hand 
			public void mouseEntered(MouseEvent e){
				choosePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e){
				choosePanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			//when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e){
				//if no word bank is chosen, show a message 
				if(choosePanel.getLetterPosition() < 0) {
					System.out.println();
					JOptionPane.showMessageDialog(null, "请选择词库！");
				}
				//otherwise switch the panel
				else {
					int letterPosition = controller.getLetterPosition();
					char wordBankChar =  (char) ('A' + controller.getLetterPosition());
					String wordBankName = wordBankChar + "";
					currentWordListResultPanel.setData(wordBankName, controller.getAllNum(letterPosition), 
							controller.getDoneNum(letterPosition), controller.getCorrectNum(letterPosition), 
							controller.getDoneNum(letterPosition) - controller.getCorrectNum(letterPosition), controller.getCorrectRate(letterPosition));
					add(currentWordListResultPanel);
					remove(choosePanel);
				}
			}
		});
		
		//add listeners to getAllResultButton
		choosePanel.getAllResultButton().addMouseListener(new MouseAdapter(){
			//when mouse enter the letter, cursor becomes a hand 
			public void mouseEntered(MouseEvent e){
				choosePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e){
				choosePanel.	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			//when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e){
				//switch panel
				allResultPanel.setData(controller.getAllNum(26), controller.getDoneNum(26), 
						controller.getCorrectNum(26), controller.getDoneNum(26) - controller.getCorrectNum(26) , 
						controller.getCorrectRate(26));
				add(allResultPanel,"allResultPanel");
				remove(choosePanel);
				pack();
				setLocationRelativeTo(null);
				cardLayout.show(getContentPane(), "allResultPanel");
			}
		});
		
		//add listeners to to letter buttons
		int i = 0;
		for(i = 0; i < 26; i++){
			choosePanel.getLetters().get(i).addMouseListener(new MouseAdapter() {
				//when mouse enter the letter, cursor becomes a hand 
				public void mouseEntered(MouseEvent e){
					choosePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				public void mouseExited(MouseEvent e){
					choosePanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
				//when mouse clicked, the button changes its bg color and panel records the clicked letter
				public void mouseClicked(MouseEvent e){
					int letterPos = Integer.parseInt(e.getSource().toString());
					choosePanel.setLetterPostion(letterPos);
					controller.setLetterPosition(letterPos);
				}
			});
		}
		
		
		
		
	}
	

}
