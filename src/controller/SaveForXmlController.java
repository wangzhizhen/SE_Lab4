package controller;

import model.Quiz;
import model.XmlFileParser;

public class SaveForXmlController implements ISaveController {

	private int letterPosition;
	public SaveForXmlController(int letterPosition){
		this.letterPosition = letterPosition;
	}
	@Override
	public void updateDictionary(Quiz quiz) {
		// TODO Auto-generated method stub
		XmlFileParser.getInstance().updateTheState(letterPosition, quiz);
	}

	@Override
	public void save(Quiz quiz, int cur) {
		// TODO Auto-generated method stub
		XmlFileParser.getInstance().saveToAllFiles(letterPosition, quiz, cur, "file/statefile.txt");
	}

}
