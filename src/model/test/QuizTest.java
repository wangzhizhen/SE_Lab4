package model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Quiz;
import model.Word;

import org.junit.Before;
import org.junit.Test;

public class QuizTest {
	Quiz quiz;

	@Test
	/*
	 * Test method for{@link model.Quiz.public Word getWordAt(int currentWordIndex)}
	 */
	public void testGetWordAt() {
		ArrayList<Word> quizList = new ArrayList<Word>();
		Word word1 = new Word("apple","n.Æ»¹û",0);
		quizList.add(word1);
		quiz = new Quiz(quizList);
		//Case 0--currentWordIndex < 0,should return null
		assertNull(quiz.getWordAt(-1));
		//Case 1--currentWordIndex:between 0 and quizList.size()-1
		assertTrue(wordEquals(word1,quiz.getWordAt(0)));
		//Case 2--currentWordIndex > quizList.size()-1£¬should return null
		assertNull(quiz.getWordAt(2));
	}
	
	public boolean wordEquals(Word word1, Word word2){
		if(!(word1.getChinese().equals(word2.getChinese())&&
				word1.getEnglish().equals(word2.getEnglish())&&
				word1.getState()==word2.getState()))
			return false;
		return true;
	}

}
