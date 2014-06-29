package controller;

public class Controller {

	private LetterPositionSettingController letterPositionSettingController = LetterPositionSettingController
			.getInstance();
	private FirstWordSettingController firstWordSettingController = FirstWordSettingController
			.getInstance();
	private NumSettingController numSettingController = NumSettingController
			.getInstance();
	private NormalQuizController normalQuizController = NormalQuizController
			.getInstance();
	private ChartDataController chartDataController = ChartDataController
			.getInstance();

	public int getLetterPosition() {
		return letterPositionSettingController.getLetterPosition();
	}

	public void setLetterPosition(int letterPosition) {
		letterPositionSettingController.setLetterPosition(letterPosition);
	}

	public int getFirstWordIndex() {
		return firstWordSettingController.getFirstWordIndex();
	}

	public int setFirstWordIndex(int option, int letterPosition, String input) {
		// option-
		// 1-first word
		// 2-from last time
		// 3-user input
		int retval = 0;// -1 first time to play, 0 correct 
		//1 invalid input, 2 already reach the end
		if (option == 1) {
			retval = firstWordSettingController.setFromFirstWord();
		} else if (option == 2) {
			retval = firstWordSettingController.setFromLastTime(letterPosition);
		} else {
			retval = firstWordSettingController.setFromUserInput(
					letterPosition, input);
		}

		return retval;

	}

	public String[] stringMatching(String input) {
		return firstWordSettingController.stringMatching(input,
				letterPositionSettingController.getLetterPosition());
	}

	public int getMaxNum(int letterPosition, int firstWordIndex) {
		return numSettingController.getMaxNum(letterPosition, firstWordIndex);

	}

	public int getNum() {
		return numSettingController.getNum();
	}

	public int setNum(String numInput) {
		int retval = 0;
		int num = 0;
		try {
			num = Integer.parseInt(numInput);
			if (num <= 0) {
				retval = 2;
			} else {
				retval = numSettingController.setNum(num);
			}
		} catch (NumberFormatException e) {
			retval = 2;
		}

		return retval;

	}

	public String normalQuizStart(int letterPosition, int firstWordIndex,
			int num) {
		normalQuizController.setFirstWordIndex(firstWordIndex);
		normalQuizController.setLetterPosition(letterPosition);
		normalQuizController.setNum(num);
		// System.out.println("firstWordIndex:-------- "+ firstWordIndex);
		// System.out.println("num:-------- "+ num);
		// System.out.println("letterPosition:-------- "+ letterPosition);
		String string = normalQuizController.start();
		return string;
	}

	public String normalQuizRun(String inputText){
		int compareResult = normalQuizController.compare(inputText);
		normalQuizController.updateQuiz(compareResult);
		String retval = null;// retval is the chinese of the next word or NULL
								// meaning end
		// check if arrive the end of this wordList
		int cur = normalQuizController.getCurrentWordIndex();
		int num = normalQuizController.getNum();
		if (cur >= num) {
			normalQuizController.stop(cur
					+ firstWordSettingController.getFirstWordIndex());
			retval = null;
		} else {
			retval = normalQuizController.next();
		}
		return retval;
	}

	public void normalQuizStop() {
		int cur = normalQuizController.getCurrentWordIndex()
				+ firstWordSettingController.getFirstWordIndex();
		normalQuizController.stop(cur);

	}

	public int getAllNum(int letterPosition) {
		return chartDataController.getAllNum(letterPosition);
	}

	public int getDoneNum(int letterPosition) {
		return chartDataController.getDoneNum(letterPosition);
	}

	public int getCorrectNum(int letterPosition) {
		return chartDataController.getCorrectNum(letterPosition);
	}

	public double getCorrectRate(int letterPosition) {
		return chartDataController.getCorrectRate(letterPosition);
	}

	public int getCompareResult() {
		// return value:1 wrong; 2 correct
		return normalQuizController.getCompareResult();
	}

	public int getCurrrenQuizAllNum() {
		return normalQuizController.getCurrentWordIndex();
	}

	public int getCurrentQuizCorrectNum() {
		return normalQuizController.getCurrentQuizCorrectNum();
	}

	public double getCurrentQuizCorrectRate() {
		if (getCurrrenQuizAllNum() == 0) {
			return 0;
		} else {
			return ((double) getCurrentQuizCorrectNum() / (double) getCurrrenQuizAllNum());
		}
	}

	public String getPreviousWordEnglish() {
		return normalQuizController.getPreviousWordEnglish();
	}
}
