package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class WordPanel extends JPanel {
	final static int STARTFROMFIRST = 1;
	final static int STARTFROMLAST = 2;
	final static int STARTFROMINPUT = 3;

	private JLabel returnToCButton = new JLabel();
	private JLabel okWButton = new JLabel();
	private JLabel FirstWord = new JLabel();
	private int option = 1;
	private JRadioButton jrbFirst = new JRadioButton();
	private JRadioButton jrbInput = new JRadioButton();
	private JRadioButton jrbLast = new JRadioButton();
	private JTextField input = new JTextField();
	
	private boolean showNote = false;
	private JLabel noteLabel1 = new JLabel();
	private JLabel noteLabel2 = new JLabel();
	private JLabel noteLabel3 = new JLabel();
	
	private String note1 = "";
	private String note2 = "";
	private String note3 = "";

	private ButtonGroup group = new ButtonGroup();

	public WordPanel() {
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);

		// north, east, west labels are used for padding
		// contentPanel holds the choose word content, buttonPanel holds the
		// buttons
		JLabel northPanel = new JLabel();
		northPanel.setOpaque(false);
		northPanel.setPreferredSize(new Dimension(0, 150));
		add(northPanel, BorderLayout.NORTH);

		JPanel contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		contentPanel.setPreferredSize(new Dimension(478, 350));
		add(contentPanel, BorderLayout.CENTER);

		JLabel westPanel = new JLabel();
		westPanel.setOpaque(false);
		westPanel.setPreferredSize(new Dimension(200, 0));
		add(westPanel, BorderLayout.WEST);

		JLabel eastPanel = new JLabel();
		eastPanel.setOpaque(false);
		eastPanel.setPreferredSize(new Dimension(200, 0));
		add(eastPanel, BorderLayout.EAST);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setPreferredSize(new Dimension(0, 100));
		add(buttonPanel, BorderLayout.SOUTH);

		// add contents to content panel and divide the content panel into two
		// parts - left and right part
		contentPanel.setLayout(new GridLayout(1, 2));

		// add radio buttons to left parts, divide the left parts into three
		// parts - first, second and third
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setOpaque(false);

		setOption(STARTFROMFIRST);
		JPanel leftFirstPanel = new JPanel();
		leftFirstPanel.setOpaque(false);
		leftFirstPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		jrbFirst.setText("从第一个单词开始");
		jrbFirst.setOpaque(false);
		jrbFirst.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		jrbFirst.setForeground(Color.DARK_GRAY);
		group.add(jrbFirst);
		leftFirstPanel.add(jrbFirst);
		leftFirstPanel.setPreferredSize(new Dimension(0, 75));
		leftPanel.add(leftFirstPanel, BorderLayout.NORTH);

		JPanel leftSecondPanel = new JPanel();
		leftSecondPanel.setOpaque(false);
		leftSecondPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		jrbLast.setText("从上一次结束处开始");
		jrbLast.setOpaque(false);
		jrbLast.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		jrbLast.setForeground(Color.DARK_GRAY);
		jrbInput.setText("输入起始单词");
		jrbLast.setOpaque(false);
		jrbInput.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		jrbInput.setForeground(Color.DARK_GRAY);
		group.add(jrbInput);
		leftSecondPanel.add(jrbLast);
		leftPanel.add(leftSecondPanel, BorderLayout.CENTER);

		JPanel leftThirdPanel = new JPanel();
		leftThirdPanel.setOpaque(false);
		leftThirdPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		group.add(jrbLast);
		leftThirdPanel.add(jrbInput);
		leftThirdPanel.setPreferredSize(new Dimension(0, 200));
		leftPanel.add(leftThirdPanel, BorderLayout.SOUTH);

		// add contents into right parts
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setOpaque(false);

		JPanel rightFirstPanel = new JPanel();
		rightFirstPanel.setOpaque(false);
		rightFirstPanel.add(FirstWord);
		rightFirstPanel.setPreferredSize(new Dimension(0, 75));
		rightPanel.add(rightFirstPanel, BorderLayout.NORTH);

		JPanel rightSecondPanel = new JPanel();
		rightSecondPanel.setOpaque(false);
		rightSecondPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		rightPanel.add(rightSecondPanel, BorderLayout.CENTER);
		JPanel rightThirdPanel = new JPanel();
		rightThirdPanel.setOpaque(false);
		rightThirdPanel.setLayout(new GridLayout(4, 1));
		rightThirdPanel.add(input);
		input.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		input.setColumns(11);

		rightThirdPanel.add(noteLabel1);
		rightThirdPanel.add(noteLabel2);
		rightThirdPanel.add(noteLabel3);
		noteLabel1.setVisible(false);
		noteLabel2.setVisible(false);
		noteLabel3.setVisible(false);
		noteLabel1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));
		noteLabel1.setForeground(Color.DARK_GRAY);
		noteLabel2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));
		noteLabel2.setForeground(Color.DARK_GRAY);
		noteLabel3.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));
		noteLabel3.setForeground(Color.DARK_GRAY);

		rightThirdPanel.setPreferredSize(new Dimension(0, 200));
		rightPanel.add(rightThirdPanel, BorderLayout.SOUTH);

		contentPanel.add(leftPanel);
		contentPanel.add(rightPanel);

		// add buttons to buttonPanel
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
		okWButton.setIcon(new ImageIcon("icons/okButton.png"));
		buttonPanel.add(okWButton);
		returnToCButton.setIcon(new ImageIcon("icons/returnButton.png"));
		buttonPanel.add(returnToCButton);
	}

	public JLabel getReturnToCButton() {
		return returnToCButton;
	}

	public JLabel getOkWButton() {
		return okWButton;
	}

	public void setOption(int option) {
		// dynamically set the default radio button
		switch (option) {
		case -1:
			jrbLast.setEnabled(false);
			group.remove(jrbLast);
			this.option = STARTFROMFIRST;
			break;
		case 1:
			this.option = option;
			jrbFirst.setSelected(true);
			group.add(jrbFirst);
			break;
		case 2:
			this.option = option;
			jrbLast.setSelected(true);
			group.add(jrbLast);
			break;
		case 3:
			this.option = option;
			jrbInput.setSelected(true);
			group.add(jrbInput);
			break;
		default:
			this.option = STARTFROMFIRST;
			jrbFirst.setSelected(true);
			group.add(jrbFirst);
			break;
		}
	}

	public int getOption() {
		return option;
	}

	public JRadioButton getJrbFirst() {
		return jrbFirst;
	}

	public JRadioButton getJrbInput() {
		return jrbInput;
	}

	public JRadioButton getJrbLastTime() {
		return jrbLast;
	}

	public JTextField getTextField() {
		return input;
	}

	public void setInputString(String s) {
		input.setText(s);

		repaint();
	}

	public void showNotes(String n1, String n2, String n3) {
		this.note1 = " " + n1;
		this.note2 = " " + n2;
		this.note3 = " " + n3;

		this.showNote = true;
		// dynamically obtained from the note word
		if(n1.equals("")){
			noteLabel1.setText("");
			noteLabel1.setVisible(false);
		}
		else{
			System.out.println("note1:"+note1);
			noteLabel1.setText(note1);
			noteLabel1.setVisible(true);
		}
		if(n2.equals("")){
			noteLabel2.setText("");
			noteLabel2.setVisible(false);
		}
		else{
			System.out.println("note2:"+note2);
			noteLabel2.setText(note2);
			noteLabel2.setVisible(true);
		}
		if(n3.equals("")){
			noteLabel3.setText("");
			noteLabel3.setVisible(false);
		}
		else{
			System.out.println("note3:"+note3);
			noteLabel3.setText(note3);
			noteLabel3.setVisible(true);
		}
		
//		noteLabel1.setText(note1);
//		noteLabel2.setText(note2);
//		noteLabel3.setText(note3);
//		noteLabel1.setVisible(showNote);
//		noteLabel2.setVisible(showNote);
//		noteLabel3.setVisible(showNote);

		repaint();
	}

	public void hideNotes(int number) {
		this.showNote = false;
		// this.note1 = "";
		// this.note2 = "";
		// this.note3 = "";
		// noteLabel1.setText(note1);
		// noteLabel2.setText(note2);
		// noteLabel3.setText(note3);
		if (number == 1) {
			noteLabel3.setVisible(showNote);
			return;
		}
		if (number == 2) {
			noteLabel2.setVisible(showNote);
			noteLabel3.setVisible(showNote);
			return;
		}
		if (number == 3) {
			noteLabel1.setVisible(showNote);
			noteLabel2.setVisible(showNote);
			noteLabel3.setVisible(showNote);
			return;
		}

		repaint();
	}

	public JLabel getNoteLabel1() {
		return noteLabel1;
	}

	public JLabel getNoteLabel2() {
		return noteLabel2;
	}

	public JLabel getNoteLabel3() {
		return noteLabel3;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// set the background image
		Image bgImg = new ImageIcon("icons/bgWordPanel.png").getImage();
		g.drawImage(bgImg, 0, 0, getSize().width, getSize().height, this);

	}

}
