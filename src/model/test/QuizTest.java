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
	public void testGetWordAt() {
		ArrayList<Word> quizList = new ArrayList<Word>();
		Word word1 = new Word("apple","n.Æ»¹û",0);
		quizList.add(word1);
		quiz = new Quiz(quizList);
		assertEquals(word1,quiz.getWordAt(0));
	}

}
