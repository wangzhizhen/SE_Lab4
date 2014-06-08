package controller;

import model.Quiz;
import model.TxtFileParser;


public class SaveToTxtController implements ISaveController {

	public void updateDictionary(Quiz quiz){
		//save the state to Dictionary
		TxtFileParser.getInstance().updateTheState(quiz);
	}
	public void save(Quiz quiz,int cur) {
		// save to the file
		TxtFileParser.getInstance().saveToAllFiles(quiz,cur,"file/statefile.txt");
	}

}
