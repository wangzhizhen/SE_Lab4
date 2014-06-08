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
	public static void setUp() throws Exception {
		Word word1 = new Word("apple","n.苹果",0);
		ArrayList<Word> wordArray1 = new ArrayList<Word>();
		wordArray1.add(word1);
		WordList wordList1 = new WordList(0,wordArray1);
		Dictionary.getInstance().getWordListArray()[0] = wordList1;
	}

	/*
	 * Test method for {@link model.Dictionary.public void setWordListAt(int letterPosition,WordList wordList)}
	 */
	@Test
	public void testSetWordListAt() {
		//Case 0--Normal case:letterPosition >= 0 and letterPosition < 26
		Word word2 = new Word("apple","n.苹果",0);
		ArrayList<Word> wordArray2 = new ArrayList<Word>();
		wordArray2.add(word2);
		WordList wordList2 = new WordList(0,wordArray2);
		Dictionary.getInstance().setWordListAt(1, wordList2);
		assertTrue(wordListEquals(wordList2,Dictionary.getInstance().getWordListArray()[1]));
	}

	

	/*
	 * Test method for {@link model.Dictionary.public boolean contains(int letterPosition, String input)}
	 */
	@Test
	public void testContains() {
		//Case 0--Test a contained word
		assertTrue(Dictionary.getInstance().contains(0, "apple"));
		//Case 1--Test a not contained word
		assertFalse(Dictionary.getInstance().contains(0, "a"));
	}

	/*
	 * Test method for {@link model.Dictionary.public int getWordListLengthAt(int letterPosition)}
	 */
	@Test
	public void testGetWordListLengthAt() {
		//Case 0--letterPosition < 0
		assertEquals(-1, Dictionary.getInstance().getWordListLengthAt(-1));
		//Case 1--letterPosition: between 0 and wordListArray.length - 1
		assertEquals(1,Dictionary.getInstance().getWordListLengthAt(0));
		//Case 2--letterPosition > wordListArray.length - 1
		assertEquals(-1, Dictionary.getInstance().getWordListLengthAt(30));
		
	}
	
	public boolean wordListEquals(WordList wordList1,WordList wordList2){
		int len1 = wordList1.getWordArray().size();
		int len2 = wordList2.getWordArray().size();
		if(len1 != len2)
			return false;
		for(int i = 0; i < len1; i ++){
			Word word1 = wordList1.getWordArray().get(i);
			Word word2 = wordList2.getWordArray().get(i);
			if(!(word1.getChinese().equals(word2.getChinese())&&
					word1.getEnglish().equals(word2.getEnglish())&&
					word1.getState()==word2.getState()))
				return false;
		}
		return true;
	}


}
