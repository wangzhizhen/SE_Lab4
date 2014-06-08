package controller;

import java.util.ArrayList;

import model.Dictionary;
import model.Quiz;
import model.Word;

public class NormalQuizController implements IQuizController {

	// implement as singleton
	private static NormalQuizController instance = new NormalQuizController();
	Quiz quiz;
	private int letterPosition;
	private int firstWordIndex;
	private int num;
	private int curWordIndex = 0;
	private int compareResult = 0;
	private int saveToken=0;

	private NormalQuizController() {

	}

	public static NormalQuizController getInstance() {
		return instance;
	}

	public String start() {
		// get the word from Dictionary and generate the quizList to construct
		// the quiz
		// return the chinese of current Word
		curWordIndex = 0;
		int i = firstWordIndex;
		ArrayList<Word> tempQuizList = new ArrayList<Word>();
		for (; i < (num + firstWordIndex); i++) {
			Word wordFromDictionaryWord =  Dictionary.getInstance().getWordAt(letterPosition,
					i);
			Word newWord = new Word(wordFromDictionaryWord.getEnglish(), wordFromDictionaryWord.getChinese(), 
					wordFromDictionaryWord.getState());
			tempQuizList.add(newWord);
		}
		quiz = new Quiz(tempQuizList);
		return quiz.getWordAt(curWordIndex).getChinese();
	}

	@Override
	public int compare(String inputText) {
		// compare whether the inputWord is correct
		Word curWord = quiz.getWordAt(curWordIndex);
		if (curWord.getEnglish().equals(inputText)) {
			compareResult = 2;
			return 2;
		} else {
			compareResult = 1;
			return 1;
		}

	}

	public void updateQuiz(int compareResult) {
		// update the Word state
		// change the correctNum\totalNum\wrongNum
		Word curWord = quiz.getWordAt(curWordIndex);
		curWord.setState(compareResult);
		if (compareResult == 2) {
			int temp = quiz.getCorrectNum() + 1;
			quiz.setCorrectNum(temp);
		} else {
			int temp = quiz.getWrongNum() + 1;
			quiz.setWrongNum(temp);
		}
		quiz.setTotalNum(quiz.getCorrectNum() + quiz.getWrongNum());
		// increase the index of curWordIndex
		curWordIndex++;
	}

	public String next() {
		// TODO Auto-generated method stub
		return quiz.getWordAt(curWordIndex).getChinese();
	}

	@Override
	public void stop(int cur) {
		// save
		SaveToTxtControllerFactory saveControllerFactory ;
//		if(this.saveToken==0){
		 saveControllerFactory = new SaveToTxtControllerFactory();
//		}
		ISaveController saveController = saveControllerFactory
				.getSaveController();
		saveController.updateDictionary(quiz);
		saveController.save(quiz, cur);

		// curWordIndex = 0;
	}

	public void setLetterPosition(int letterPosition) {
		this.letterPosition = letterPosition;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setFirstWordIndex(int firstWordIndex) {
		this.firstWordIndex = firstWordIndex;
	}

	public int getCurrentWordIndex() {
		// TODO Auto-generated method stub
		return curWordIndex;
	}

	public int getNum() {
		// TODO Auto-generated method stub
		return num;
	}

	public int getCurrentQuizCorrectNum() {
		return quiz.getCorrectNum();
	}

	public int getCompareResult() {
		// TODO Auto-generated method stub
		return compareResult;
	}

	public String getPreviousWordEnglish() {
		return quiz.getQuizList().get(curWordIndex - 1).getEnglish();
	}

}
