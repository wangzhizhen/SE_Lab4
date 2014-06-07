package model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Word;
import model.WordList;

import org.junit.Before;
import org.junit.Test;

public class WordListTest {
	private WordList wordList;

	@Before
	public void setUp() throws Exception {
		wordList = new WordList();
	}

	@Test
	public void testSetWord() {
		Word word1 = new Word("apple","n.Æ»¹û",0);
		Word word2 = new Word("banana","n.Ïã½¶",0);
		ArrayList<Word> wordArray1 = new ArrayList<Word>();
		ArrayList<Word> wordArray2 = new ArrayList<Word>();
		wordArray1.add(word1);
		wordArray2.add(word2);
		wordList.setWordArray(wordArray1);
		wordList.setWordAt(0, word2);
		assertEquals(wordArray2,wordList.getWordArray());
	}

}
