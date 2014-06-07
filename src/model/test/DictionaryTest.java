package model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Dictionary;
import model.Word;
import model.WordList;

import org.junit.BeforeClass;
import org.junit.Test;

public class DictionaryTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Word word1 = new Word("apple","n.Æ»¹û",0);
		ArrayList<Word> wordArray1 = new ArrayList<Word>();
		wordArray1.add(word1);
		WordList wordList1 = new WordList(0,wordArray1);
		Dictionary.getInstance().getWordListArray()[0] = wordList1;
	}

	@Test
	public void testSetWordListAt() {
		Word word2 = new Word("apple","n.Æ»¹û",0);
		ArrayList<Word> wordArray2 = new ArrayList<Word>();
		wordArray2.add(word2);
		WordList wordList2 = new WordList(0,wordArray2);
		Dictionary.getInstance().setWordListAt(1, wordList2);
		assertEquals(wordList2,Dictionary.getInstance().getWordListArray()[1]);
	}

	@Test
	public void testContains() {
		assertEquals(true,Dictionary.getInstance().contains(0, "apple"));
	}

	@Test
	public void testGetWordListLengthAt() {
		assertEquals(1,Dictionary.getInstance().getWordListLengthAt(0));
	}

}
