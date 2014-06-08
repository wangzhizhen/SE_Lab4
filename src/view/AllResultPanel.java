package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

@SuppressWarnings("serial")
public class AllResultPanel extends JPanel {
	private JLabel returnToC = new JLabel(new ImageIcon(
			"icons/returnMainButton.png"));
	private JTable table = new JTable();
	/* datas needed for drawing the charts */
	private DefaultPieDataset finishedPieDataset = new DefaultPieDataset();
	private DefaultPieDataset totoalPieDataset = new DefaultPieDataset();
	private DefaultCategoryDataset finishedBarDataset = new DefaultCategoryDataset();
	private DefaultCategoryDataset totalBarDataset = new DefaultCategoryDataset();
	private JFreeChart finishedPieChart = ChartFactory.createPieChart(
			"已背单词统计信息", finishedPieDataset, false, false, false);
	private JFreeChart totalPieChart = ChartFactory.createPieChart("全部单词统计信息",
			totoalPieDataset, false, false, false);
	private JFreeChart finishedBarChart = ChartFactory.createBarChart(
			"已背单词统计信息", "分类", "数量", finishedBarDataset,
			PlotOrientation.VERTICAL, false, false, false);
	private JFreeChart totalBarChart = ChartFactory.createBarChart("全部单词统计信息",
			"分类", "数量", totalBarDataset, PlotOrientation.VERTICAL, false,
			false, false);
	private ChartPanel finishedPiePanel = new ChartPanel(finishedPieChart);
	private ChartPanel totalPiePanel = new ChartPanel(totalPieChart);
	private ChartPanel finishedBarPanel = new ChartPanel(finishedBarChart);
	private ChartPanel totalBarPanel = new ChartPanel(totalBarChart);

	public AllResultPanel() {
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);

		// north, east, west labels are used for padding
		// letterPanel holds the letters, buttonPanel holds the buttons
		JLabel northPanel = new JLabel();
		northPanel.setOpaque(false);
		northPanel.setPreferredSize(new Dimension(1122, 125));
		add(northPanel, BorderLayout.NORTH);

		JPanel tablePanel = new JPanel();
		tablePanel.setOpaque(false);
		// tablePanel.setBackground(Color.RED);
		tablePanel.setPreferredSize(new Dimension(0, 105));
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
		chartPanel.setPreferredSize(new Dimension(0, 370));
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
		// chartPanel.add(returnToC);

		JPanel leftPanel = new JPanel();
		leftPanel.setOpaque(false);
		leftPanel.setPreferredSize(new Dimension(10, 0));
		chartPanel.add(leftPanel, BorderLayout.WEST);

		JPanel centerChartPanel = new JPanel();
		chartPanel.add(centerChartPanel);
		// centerChartPanel.setBackground(Color.RED);

		JPanel rightPanel = new JPanel();
		rightPanel.setOpaque(false);
		rightPanel.setPreferredSize(new Dimension(10, 0));
		chartPanel.add(rightPanel, BorderLayout.EAST);

		JPanel southPanel = new JPanel();
		southPanel.setOpaque(false);
		southPanel.setPreferredSize(new Dimension(0, 90));
		chartPanel.add(southPanel, BorderLayout.SOUTH);

		centerChartPanel.setLayout(new GridLayout(1, 4));
		centerChartPanel.add(finishedPiePanel);
		centerChartPanel.add(totalPiePanel);
		centerChartPanel.add(finishedBarPanel);
		centerChartPanel.add(totalBarPanel);

		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
		southPanel.add(returnToC);

		// for show Chinese
		finishedBarChart.getTitle().setFont(
				new Font("Microsoft YaHei", Font.BOLD, 20));
		finishedPieChart.getTitle().setFont(
				new Font("Microsoft YaHei", Font.BOLD, 20));
		totalPieChart.getTitle().setFont(
				new Font("Microsoft YaHei", Font.BOLD, 20));
		totalBarChart.getTitle().setFont(
				new Font("Microsoft YaHei", Font.BOLD, 20));
		((PiePlot) finishedPieChart.getPlot()).setLabelFont(new Font(
				"Microsoft YaHei", Font.PLAIN, 15));
		((PiePlot) totalPieChart.getPlot()).setLabelFont(new Font(
				"Microsoft YaHei", Font.PLAIN, 15));
		CategoryPlot plot = (CategoryPlot) finishedBarChart.getPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setTickLabelFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		domainAxis.setLabelFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		ValueAxis valueAxis = plot.getRangeAxis();
		// valueAxis.setAutoRangeMinimumSize(1);
		// valueAxis.setAutoRange(true);
		// valueAxis.setLowerBound(0);
		valueAxis.setTickLabelFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		valueAxis.setLabelFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		plot = (CategoryPlot) totalBarChart.getPlot();
		domainAxis = plot.getDomainAxis();
		domainAxis.setTickLabelFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		domainAxis.setLabelFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		ValueAxis valueAxis2 = plot.getRangeAxis();
		valueAxis2.setTickLabelFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		valueAxis2.setLabelFont(new Font("Microsoft YaHei", Font.BOLD, 15));

	}

	public void setData(int totalWordNum, int finishedWordNum,
			int correntWordNum, int wrongWordNum, double correctRatio) {

		finishedPieDataset.setValue("正确", correntWordNum);
		finishedPieDataset.setValue("错误", wrongWordNum);

		totoalPieDataset.setValue("已背", finishedWordNum);
		totoalPieDataset.setValue("未背", totalWordNum - finishedWordNum);

		((PiePlot) finishedPieChart.getPlot()).setSimpleLabels(true);
		((PiePlot) totalPieChart.getPlot()).setSimpleLabels(true);

		finishedBarDataset.addValue(correntWordNum, "", "正确");
		finishedBarDataset.addValue(wrongWordNum, "", "错误");

		totalBarDataset.addValue(finishedWordNum, "", "已背");
		totalBarDataset.addValue(totalWordNum - finishedWordNum, "", "未背");

		setTableContent(totalWordNum, finishedWordNum, correntWordNum,
				wrongWordNum, correctRatio);

		repaint();

	}

	private void setTableContent(int totalWordNum, int finishedWordNum,
			int correntWordNum, int wrongWordNum, double correctRatio) {
		String[] columns = { "全部词库单词总数", "已背单词数", "正确单词数", "错误单词数", "正确率" };
		// insert the data
		NumberFormat nmf = NumberFormat.getInstance();
		nmf.setMaximumFractionDigits(2);
		String correctRatioString = nmf.format(correctRatio * 100) + "%";

		Object[][] arr = new Object[1][5];
		arr[0][0] = totalWordNum;
		arr[0][1] = finishedWordNum;
		arr[0][2] = correntWordNum;
		arr[0][3] = wrongWordNum;
		arr[0][4] = correctRatioString;

		DefaultTableModel model = new DefaultTableModel(arr, columns);
		table.setModel(model);
		// update the table
		table.invalidate();

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// set the background image
		Image bgImg = new ImageIcon("icons/bgAllResultPanel.png").getImage();
		g.drawImage(bgImg, 0, 0, getSize().width, getSize().height, this);

	}

	public JLabel getReturnButton() {
		return returnToC;
	}

}
