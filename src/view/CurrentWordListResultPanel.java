package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

@SuppressWarnings("serial")
public class CurrentWordListResultPanel extends JPanel {
	private JLabel returnButton = new JLabel(new ImageIcon(
			"icons/returnMainButton.png"));
	private JTable table = new JTable();

	private DefaultPieDataset finishedDataset = new DefaultPieDataset();
	private DefaultPieDataset totoalDataset = new DefaultPieDataset();
	private JFreeChart finishedPieChart = ChartFactory.createPieChart(
			"已背单词统计信息", finishedDataset, false, false, false);
	private JFreeChart totalPieChart = ChartFactory.createPieChart("全部单词统计信息",
			totoalDataset, false, false, false);
	private ChartPanel finishedPiePanel = new ChartPanel(finishedPieChart);
	private ChartPanel totalPiePanel = new ChartPanel(totalPieChart);

	public CurrentWordListResultPanel() {
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);

		// north, east, west labels are used for padding
		// letterPanel holds the letters, buttonPanel holds the buttons
		JLabel northPanel = new JLabel();
		northPanel.setOpaque(false);
		northPanel.setPreferredSize(new Dimension(0, 110));
		add(northPanel, BorderLayout.NORTH);

		JPanel tablePanel = new JPanel();
		tablePanel.setOpaque(false);
		tablePanel.setPreferredSize(new Dimension(0, 80));
		add(tablePanel, BorderLayout.CENTER);

		JLabel westPanel = new JLabel();
		westPanel.setOpaque(false);
		westPanel.setPreferredSize(new Dimension(100, 0));
		add(westPanel, BorderLayout.WEST);

		JLabel eastPanel = new JLabel();
		eastPanel.setOpaque(false);
		eastPanel.setPreferredSize(new Dimension(100, 0));
		add(eastPanel, BorderLayout.EAST);

		JPanel chartPanel = new JPanel();
		chartPanel.setOpaque(false);
		chartPanel.setPreferredSize(new Dimension(300, 410));
		add(chartPanel, BorderLayout.SOUTH);

		// add JTable to table panel
		table = new JTable();
		JScrollPane tablePane = new JScrollPane(table);
		table.getTableHeader().setFont(
				new Font("Microsoft YaHei", Font.PLAIN, 17));
		table.setFont(new Font("Microsoft YaHei", Font.PLAIN, 17));
		table.setRowHeight(28);
		table.setGridColor(Color.BLACK);
		table.setEnabled(false);
		tablePane.setPreferredSize(new Dimension(678, 58));
		tablePanel.add(tablePane);

		// add charts to chart panel
		chartPanel.setLayout(new BorderLayout());

		JPanel leftPanel = new JPanel();
		leftPanel.setOpaque(false);
		leftPanel.setPreferredSize(new Dimension(70, 0));
		chartPanel.add(leftPanel, BorderLayout.WEST);

		JPanel centerChartPanel = new JPanel();
		chartPanel.add(centerChartPanel);
		centerChartPanel.setBackground(Color.RED);

		JPanel rightPanel = new JPanel();
		rightPanel.setOpaque(false);
		rightPanel.setPreferredSize(new Dimension(70, 0));
		chartPanel.add(rightPanel, BorderLayout.EAST);

		JPanel southPanel = new JPanel();
		southPanel.setOpaque(false);
		southPanel.setPreferredSize(new Dimension(0, 60));
		chartPanel.add(southPanel, BorderLayout.SOUTH);

		centerChartPanel.setLayout(new GridLayout(1, 2));
		centerChartPanel.add(finishedPiePanel);
		centerChartPanel.add(totalPiePanel);

		southPanel.add(returnButton);

		// for showing chinese
		TextTitle textTitle = finishedPieChart.getTitle();
		textTitle.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
		textTitle = totalPieChart.getTitle();
		textTitle.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
		((PiePlot) finishedPieChart.getPlot()).setLabelFont(new Font(
				"Microsoft YaHei", Font.PLAIN, 15));
		((PiePlot) totalPieChart.getPlot()).setLabelFont(new Font(
				"Microsoft YaHei", Font.PLAIN, 15));

	}

	public void setData(String wordBankName, int totalWordNum,
			int finishedWordNum, int correntWordNum, int wrongWordNum,
			double correctRatio) {

		finishedDataset.setValue("正确", correntWordNum);
		finishedDataset.setValue("错误", wrongWordNum);

		totoalDataset.setValue("已背", finishedWordNum);
		totoalDataset.setValue("未背", totalWordNum - finishedWordNum);

		((PiePlot) finishedPieChart.getPlot()).setSimpleLabels(true);
		((PiePlot) totalPieChart.getPlot()).setSimpleLabels(true);

		setTableContent(wordBankName, totalWordNum, finishedWordNum,
				correntWordNum, wrongWordNum, correctRatio);

		repaint();

	}

	public JLabel getReturnButton() {
		return returnButton;
	}

	private void setTableContent(String wordBankName, int totalWordNum,
			int finishedWordNum, int correntWordNum, int wrongWordNum,
			double correctRatio) {
		String[] columns = { "词库名", "词库单词总数", "已背单词数", "正确单词数", "错误单词数", "正确率" };
		// insert the data
		NumberFormat nmf = NumberFormat.getInstance();
		nmf.setMaximumFractionDigits(2);
		System.out.println(correctRatio);
		String correctRatioString = nmf.format(correctRatio * 100) + "%";

		Object[][] arr = new Object[1][6];
		arr[0][0] = wordBankName;
		arr[0][1] = totalWordNum;
		arr[0][2] = finishedWordNum;
		arr[0][3] = correntWordNum;
		arr[0][4] = wrongWordNum;
		arr[0][5] = correctRatioString;

		DefaultTableModel model = new DefaultTableModel(arr, columns);
		table.setModel(model);
		// update the table
		table.invalidate();

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// set the background image
		Image bgImg = new ImageIcon("icons/bgCurrentResultPanel.png")
				.getImage();
		g.drawImage(bgImg, 0, 0, getSize().width, getSize().height, this);

	}

}
