package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeSet;

import org.junit.BeforeClass;
import org.junit.Test;

public class TxtFileParserTest {
	
	@BeforeClass
	public static void setUp() throws Exception {
	}

	@Test
	public void testReadFromTxtFile() {
		TreeSet<String> dictionaryTree = new TreeSet<String>();
		WordList[] wordListArray = new WordList[26];
		ArrayList<Word> wordArray1 = new ArrayList<Word>();
		ArrayList<Word> wordArray2 = new ArrayList<Word>();
		ArrayList<Word> wordArray3 = new ArrayList<Word>();
		ArrayList<Word> wordArray4 = new ArrayList<Word>();
		ArrayList<Word> wordArray5 = new ArrayList<Word>();
		ArrayList<Word> wordArray6 = new ArrayList<Word>();
		ArrayList<Word> wordArray7 = new ArrayList<Word>();
		ArrayList<Word> wordArray8 = new ArrayList<Word>();
		ArrayList<Word> wordArray9 = new ArrayList<Word>();
		ArrayList<Word> wordArray10 = new ArrayList<Word>();
		ArrayList<Word> wordArray11 = new ArrayList<Word>();
		ArrayList<Word> wordArray12 = new ArrayList<Word>();
		ArrayList<Word> wordArray13 = new ArrayList<Word>();
		ArrayList<Word> wordArray14 = new ArrayList<Word>();
		ArrayList<Word> wordArray15 = new ArrayList<Word>();
		ArrayList<Word> wordArray16 = new ArrayList<Word>();
		ArrayList<Word> wordArray17 = new ArrayList<Word>();
		ArrayList<Word> wordArray18 = new ArrayList<Word>();
		ArrayList<Word> wordArray19 = new ArrayList<Word>();
		ArrayList<Word> wordArray20 = new ArrayList<Word>();
		ArrayList<Word> wordArray21 = new ArrayList<Word>();
		ArrayList<Word> wordArray22 = new ArrayList<Word>();
		ArrayList<Word> wordArray23 = new ArrayList<Word>();
		ArrayList<Word> wordArray24 = new ArrayList<Word>();
		ArrayList<Word> wordArray25 = new ArrayList<Word>();
		ArrayList<Word> wordArray26 = new ArrayList<Word>();
		dictionaryTree.add("apple");
		dictionaryTree.add("banana");
		dictionaryTree.add("car");
		dictionaryTree.add("dark");
		dictionaryTree.add("ear");
		dictionaryTree.add("far");
		dictionaryTree.add("goose");
		dictionaryTree.add("hint");
		dictionaryTree.add("ice");
		dictionaryTree.add("jack");
		dictionaryTree.add("knife");
		dictionaryTree.add("light");
		dictionaryTree.add("mother");
		dictionaryTree.add("need");
		dictionaryTree.add("old");
		dictionaryTree.add("paper");
		dictionaryTree.add("queen");
		dictionaryTree.add("rabit");
		dictionaryTree.add("snack");
		dictionaryTree.add("tee");
		dictionaryTree.add("unique");
		dictionaryTree.add("violin");
		dictionaryTree.add("war");
		dictionaryTree.add("x-ray");
		dictionaryTree.add("yellow");
		dictionaryTree.add("zoo");
		wordArray1.add(new Word("apple","n.苹果",0));
		wordArray2.add(new Word("banana","n.香蕉",0));
		wordArray3.add(new Word("car","n.小汽车",0));
		wordArray4.add(new Word("dark","a.黑的",0));
		wordArray5.add(new Word("ear","n.耳朵",0));
		wordArray6.add(new Word("far","a.远的",0));
		wordArray7.add(new Word("goose","n.天鹅",0));
		wordArray8.add(new Word("hint","n.提示",0));
		wordArray9.add(new Word("ice","n.冰",0));
		wordArray10.add(new Word("jacket","n.夹克",0));
		wordArray11.add(new Word("knife","n.小刀",0));
		wordArray12.add(new Word("light","n.光",0));
		wordArray13.add(new Word("mother","n.母亲",0));
		wordArray14.add(new Word("need","v.需要",0));
		wordArray15.add(new Word("old","a.旧的",0));
		wordArray16.add(new Word("paper","n.纸",0));
		wordArray17.add(new Word("queen","n.皇后",0));
		wordArray18.add(new Word("rabit","n.兔子",0));
		wordArray19.add(new Word("snack","n.点心",0));
		wordArray20.add(new Word("tee","n.茶",0));
		wordArray21.add(new Word("unique","n.唯一的",0));
		wordArray22.add(new Word("violin","n.小提琴",0));
		wordArray23.add(new Word("war","n.战争",0));
		wordArray24.add(new Word("x-ray","n.x射线",0));
		wordArray25.add(new Word("yellow","a.黄色的",0));
		wordArray26.add(new Word("zoo","n.动物园",0));
		WordList wordList1 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(0),wordArray1);
		WordList wordList2 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(1),wordArray2);
		WordList wordList3 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(2),wordArray3);
		WordList wordList4 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(3),wordArray4);
		WordList wordList5 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(4),wordArray5);
		WordList wordList6 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(5),wordArray6);
		WordList wordList7 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(6),wordArray7);
		WordList wordList8 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(7),wordArray8);
		WordList wordList9 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(8),wordArray9);
		WordList wordList10 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(9),wordArray10);
		WordList wordList11 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(10),wordArray11);
		WordList wordList12 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(11),wordArray12);
		WordList wordList13 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(12),wordArray13);
		WordList wordList14 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(13),wordArray14);
		WordList wordList15 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(14),wordArray15);
		WordList wordList16 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(15),wordArray16);
		WordList wordList17 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(16),wordArray17);
		WordList wordList18 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(17),wordArray18);
		WordList wordList19 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(18),wordArray19);
		WordList wordList20 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(19),wordArray20);
		WordList wordList21 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(20),wordArray21);
		WordList wordList22 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(21),wordArray22);
		WordList wordList23 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(22),wordArray23);
		WordList wordList24 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(23),wordArray24);
		WordList wordList25 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(24),wordArray25);
		WordList wordList26 = new WordList(TxtFileParser.getInstance().readLastTimeIndexFile(25),wordArray26);
		wordListArray[0] = wordList1;
		wordListArray[1] = wordList2;
		wordListArray[2] = wordList3;
		wordListArray[3] = wordList4;
		wordListArray[4] = wordList5;
		wordListArray[5] = wordList6;
		wordListArray[6] = wordList7;
		wordListArray[7] = wordList8;
		wordListArray[8] = wordList9;
		wordListArray[9] = wordList10;
		wordListArray[10] = wordList11;
		wordListArray[11] = wordList12;
		wordListArray[12] = wordList13;
		wordListArray[13] = wordList14;
		wordListArray[14] = wordList15;
		wordListArray[15] = wordList16;
		wordListArray[16] = wordList17;
		wordListArray[17] = wordList18;
		wordListArray[18] = wordList19;
		wordListArray[19] = wordList20;
		wordListArray[20] = wordList21;
		wordListArray[21] = wordList22;
		wordListArray[22] = wordList23;
		wordListArray[23] = wordList24;
		wordListArray[24] = wordList25;
		wordListArray[25] = wordList26;
		TxtFileParser.getInstance().readFromTxtFile("file/testfile.txt");
		assertEquals(true,Dictionary.getInstance().getDictionaryTree().contains("apple"));
		assertEquals(true,Dictionary.getInstance().getDictionaryTree().contains("banana"));
		
		//assertEquals(wordListArray,Dictionary.getInstance().getWordListArray());
		
	}

	@Test
	public void testInitTheStateFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStateStatistics() {
		
	}

	@Test
	public void testUpdateTheState() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyTheStateFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadLastTimeIndexFile() {
		TxtFileParser.getInstance().setLastTimeIndexFile(0, 1);
		assertEquals(1,TxtFileParser.getInstance().readLastTimeIndexFile(0));
	}

	@Test
	public void testSetLastTimeIndexFile() {
		TxtFileParser.getInstance().setLastTimeIndexFile(0, 300);
		assertEquals(300,TxtFileParser.getInstance().readLastTimeIndexFile(0));
	}

	@Test
	public void testSaveToAllFiles() {
		fail("Not yet implemented");
	}

}
