package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class QuizResultPanel extends JPanel {
	private JTable table = new JTable();
	private JLabel returnButton = new JLabel(new ImageIcon(
			"icons/returnMainButton.png"));

	public QuizResultPanel() {
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);

		// north, east, west labels are used for padding
		// contentPanel holds the choose word content, buttonPanel holds the
		// buttons
		JLabel northPanel = new JLabel();
		northPanel.setOpaque(false);
		northPanel.setPreferredSize(new Dimension(0, 128));
		add(northPanel, BorderLayout.NORTH);

		JPanel tablePanel = new JPanel();
		tablePanel.setOpaque(false);
		tablePanel.setPreferredSize(new Dimension(678, 100));
		add(tablePanel, BorderLayout.CENTER);

		JLabel westPanel = new JLabel();
		westPanel.setOpaque(false);
		westPanel.setPreferredSize(new Dimension(100, 0));
		add(westPanel, BorderLayout.WEST);

		JLabel eastPanel = new JLabel();
		eastPanel.setOpaque(false);
		eastPanel.setPreferredSize(new Dimension(100, 0));
		add(eastPanel, BorderLayout.EAST);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setPreferredSize(new Dimension(0, 100));
		add(buttonPanel, BorderLayout.SOUTH);

		// add contents to contentPanel
		// add JTable to table panel
		table = new JTable();
		setTableContent("A", 0, 0, 0, 0);

		JScrollPane tablePane = new JScrollPane(table);
		table.getTableHeader().setFont(
				new Font("Microsoft YaHei", Font.PLAIN, 20));
		table.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		table.setRowHeight(28);
		table.setGridColor(Color.BLACK);
		table.setEnabled(false);
		tablePane.setPreferredSize(new Dimension(678, 63));
		tablePanel.add(tablePane);

		// add buttons to buttonPanel
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
		buttonPanel.add(returnButton);
	}

	public void setData(String wordBankName, int totalWordNum,
			int correntWordNum, int wrongWordNum, double correctRatio) {

		setTableContent(wordBankName, totalWordNum, correntWordNum,
				wrongWordNum, correctRatio);

		repaint();

	}

	private void setTableContent(String wordBankName, int totalWordNum,
			int correntWordNum, int wrongWordNum, double correctRatio) {
		String[] columns = { "所选词库名", "所选单词数量", "正确单词数", "错误单词数", "正确率" };
		// insert the data
		NumberFormat nmf = NumberFormat.getInstance();
		nmf.setMaximumFractionDigits(2);
		System.out.println(correctRatio);
		String correctRatioString = nmf.format(correctRatio * 100) + "%";

		Object[][] arr = new Object[1][5];
		arr[0][0] = wordBankName;
		arr[0][1] = totalWordNum;
		arr[0][2] = correntWordNum;
		arr[0][3] = wrongWordNum;
		arr[0][4] = correctRatioString;

		DefaultTableModel model = new DefaultTableModel(arr, columns);
		table.setModel(model);
		// update the table
		table.invalidate();

	}

	public JLabel getReturnButton() {
		return this.returnButton;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// set the background image
		Image bgImg = new ImageIcon("icons/bgQuizResultPanel.png").getImage();
		g.drawImage(bgImg, 0, 0, getSize().width, getSize().height, this);

	}

}
