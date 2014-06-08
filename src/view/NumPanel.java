package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NumPanel extends JPanel {
	private JLabel returnToW = new JLabel();
	private JLabel inputLabel = new JLabel("输入数量:");
	private JTextField input = new JTextField();
	private int maxNum = 0;
	private JLabel inputNote = new JLabel();
	private JLabel okNButton = new JLabel();

	public NumPanel() {
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);

		// north, east, west labels are used for padding
		// contentPanel holds the choose word content, buttonPanel holds the
		// buttons
		JLabel northPanel = new JLabel();
		northPanel.setOpaque(false);
		northPanel.setPreferredSize(new Dimension(0, 120));
		add(northPanel, BorderLayout.NORTH);

		JPanel contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		contentPanel.setPreferredSize(new Dimension(478, 280));
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
		buttonPanel.setPreferredSize(new Dimension(0, 200));
		add(buttonPanel, BorderLayout.SOUTH);

		// add contents to content panel and divide the content panel into two
		// parts - left and right part
		contentPanel.setLayout(new GridLayout(1, 2));

		// add radio buttons to left parts, divide the left parts into three
		// parts - first, second and third
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setOpaque(false);

		JPanel leftFirstPanel = new JPanel();
		leftFirstPanel.setOpaque(false);
		leftFirstPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		leftFirstPanel.add(inputLabel);
		inputLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		leftFirstPanel.setPreferredSize(new Dimension(0, 80));
		leftPanel.add(leftFirstPanel, BorderLayout.NORTH);

		JPanel leftSecondPanel = new JPanel();
		leftSecondPanel.setOpaque(false);
		leftSecondPanel.setPreferredSize(new Dimension(0, 200));
		leftPanel.add(leftSecondPanel, BorderLayout.SOUTH);

		// add contents into right parts
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setOpaque(false);

		JPanel rightFirstPanel = new JPanel();
		rightFirstPanel.setOpaque(false);
		rightFirstPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		rightFirstPanel.add(input);
		input.setColumns(11);
		input.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		rightPanel.add(rightFirstPanel, BorderLayout.NORTH);

		JPanel rightSecondPanel = new JPanel();
		rightSecondPanel.setOpaque(false);
		rightSecondPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel inputNoteChinese = new JLabel("本词库剩余单词数：");
		inputNoteChinese.setFont(new Font("Microsoft YaHei", Font.PLAIN, 19));
		rightSecondPanel.add(inputNoteChinese);
		inputNote.setText(String.valueOf(maxNum));
		inputNote.setFont(new Font("Microsoft YaHei", Font.PLAIN, 19));
		inputNote.setForeground(Color.RED);
		rightSecondPanel.add(inputNote);
		rightSecondPanel.setPreferredSize(new Dimension(0, 235));
		rightPanel.add(rightSecondPanel, BorderLayout.SOUTH);

		contentPanel.add(leftPanel);
		contentPanel.add(rightPanel);

		// add buttons to buttonPanel
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 110));
		okNButton.setIcon(new ImageIcon("icons/okButton.png"));
		buttonPanel.add(okNButton);
		returnToW.setIcon(new ImageIcon("icons/returnButton.png"));
		buttonPanel.add(returnToW);

	}

	public JLabel getReturnToWButton() {
		return returnToW;
	}

	public JLabel getOkNButton() {
		return okNButton;
	}

	public JTextField getTextField() {
		return input;
	}

	public void setInputNote(int maxNum) {
		System.out.println(this.maxNum);
		this.maxNum = maxNum;
		inputNote.setText(String.valueOf(this.maxNum));

		repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// set the background image
		Image bgImg = new ImageIcon("icons/bgNumPanel.png").getImage();
		g.drawImage(bgImg, 0, 0, getSize().width, getSize().height, this);

	}

}
