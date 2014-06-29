package controller;

import model.XmlFileParser;

public class ChartDataController {

	int allNum;
	int doneNum;
	int correctNum;
	// implement as singleton
	private static ChartDataController instance = new ChartDataController();
//merge
	private ChartDataController() {

	}

	public int getAllNum(int letterPosition) {
		return XmlFileParser.getInstance().getStateStatistics(letterPosition)[0];
	}

	public int getDoneNum(int letterPosition) {
		return XmlFileParser.getInstance().getStateStatistics(letterPosition)[1];
	}

	public int getCorrectNum(int letterPosition) {
		return XmlFileParser.getInstance().getStateStatistics(letterPosition)[2];
	}

	public double getCorrectRate(int letterPosition) {
		double correctRate = 0;
		// check if doneNum is zero
		if (getDoneNum(letterPosition) == 0) {
			correctRate = 0;
		} else {
			correctRate = (double) getCorrectNum(letterPosition)
					/ ((double) getDoneNum(letterPosition));
		}
		return correctRate;
	}

	public static ChartDataController getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

}
