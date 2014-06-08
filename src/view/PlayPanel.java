package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PlayPanel extends JPanel {
	// private int wordNum;
	// private String initWord;
	private JLabel chinese = new JLabel();
	private JTextField inputWord = new JTextField();;
	private JLabel next = new JLabel();
	private JLabel saveAndExit = new JLabel();
	private JLabel answerNote = new JLabel();

	public PlayPanel() {
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);

		// north, east, west labels are used for padding
		// contentPanel holds the choose word content, buttonPanel holds the
		// buttons
		JLabel northPanel = new JLabel();
		northPanel.setOpaque(false);
		northPanel.setPreferredSize(new Dimension(0, 200));
		add(northPanel, BorderLayout.NORTH);

		JPanel contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		contentPanel.setPreferredSize(new Dimension(478, 150));
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
		buttonPanel.setPreferredSize(new Dimension(0, 250));
		add(buttonPanel, BorderLayout.SOUTH);

		// add contents to contentPanel
		contentPanel.setLayout(new BorderLayout());
		chinese.setFont(new Font("Microsoft YaHei", Font.PLAIN, 25));
		contentPanel.add(chinese, BorderLayout.NORTH);

		JPanel wordPanel = new JPanel();
		wordPanel.setOpaque(false);
		wordPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));
		JLabel inputLabel = new JLabel("输入单词：");
		inputLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		wordPanel.add(inputLabel);
		inputWord.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		inputWord.setColumns(11);
		wordPanel.add(inputWord);
		wordPanel.setPreferredSize(new Dimension(0, 50));
		contentPanel.add(wordPanel, BorderLayout.CENTER);

		JPanel answerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		answerPanel.setOpaque(false);
		answerPanel.add(answerNote);
		answerNote.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		answerPanel.setPreferredSize(new Dimension(0, 50));
		contentPanel.add(answerPanel, BorderLayout.SOUTH);

		// add buttons to buttonPanel
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 110));
		next.setIcon(new ImageIcon("icons/nextButton.png"));
		buttonPanel.add(next);
		saveAndExit.setIcon(new ImageIcon("icons/exitButton.png"));
		buttonPanel.add(saveAndExit);

	}

	public void setChinese(String chineseString) {
		this.chinese.setText(chineseString);
		this.inputWord.setText("");
		this.answerNote.setText("");

		repaint();

	}

	public void setAnswerNote(String correctWord, int compareResult) {
		// the answer is wrong
		if (compareResult == 1) {
			answerNote.setForeground(new Color(211, 110, 53));
			answerNote.setText("回答错误，正确拼写：" + correctWord);
			repaint();

		} else {
			answerNote.setForeground(new Color(111, 152, 57));
			answerNote.setText("回答正确");
			repaint();
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// set the background image
		Image bgImg = new ImageIcon("icons/bgPlayPanel.png").getImage();
		g.drawImage(bgImg, 0, 0, getSize().width, getSize().height, this);

	}

	public JTextField getTextField() {
		return inputWord;
	}

	public JLabel getNextButton() {
		return next;
	}

	public JLabel getSaveAndExitButton() {
		return saveAndExit;
	}

}
