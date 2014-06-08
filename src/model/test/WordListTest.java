package model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Word;
import model.WordList;

import org.junit.Before;
import org.junit.Test;

public class WordListTest {
	private WordList wordList1;

	@Before
	public void setUp() throws Exception {
		wordList1 = new WordList();
	}

	@Test
	public void testSetWord() {
		Word word1 = new Word("apple","n.Æ»¹û",0);
		Word word2 = new Word("banana","n.Ïã½¶",0);
		Word word3 = new Word("car","n.Ð¡Æû³µ",0);
		ArrayList<Word> wordArray1 = new ArrayList<Word>();
		ArrayList<Word> wordArray2 = new ArrayList<Word>();
		wordArray1.add(word1);
		wordArray1.add(word2);
		wordArray2.add(word1);
		wordArray2.add(word3);
//		wordList1.setWordArray(wordArray1);
		wordList1.setWordAt(1, word3);
		WordList wordList2 = new WordList();
//		wordList2.setWordArray(wordArray2);
		assertTrue(wordListEquals(wordList2,wordList1));
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
