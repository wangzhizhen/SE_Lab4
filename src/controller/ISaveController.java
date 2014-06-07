package controller;

import model.Quiz;

public interface ISaveController {

	public void updateDictionary(Quiz quiz);
	public void save(Quiz quiz, int cur);
}
