package controller.test;

import static org.junit.Assert.*;

import model.TxtFileParser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.FirstWordSettingController;

public class TestFirstWordSettingController {

	FirstWordSettingController controller;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		controller = FirstWordSettingController.getInstance();
		TxtFileParser.getInstance().init("file/txtfile.txt");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetFromFirstWord() {
		assertEquals(0, controller.setFromFirstWord());
	}

	@Test
	public void testGetFirstWordIndex() {
		
		assertEquals(0, controller.getFirstWordIndex());
	}
	@Test
	public void testSetFromUserInput(){
		//param is "Test"
		
		assertEquals(1,testSetFromUserInputWithParam(0,"Test"));
		assertEquals(0, controller.getFirstWordIndex());
		
		//param is "1234"
		assertEquals(1,testSetFromUserInputWithParam(0,"1234"));
		assertEquals(0, controller.getFirstWordIndex());
		
		//param is "abortion"
		int pos = getLetterPosition("abortion");
		System.out.println("pos is -------" +pos);
		assertEquals(0,testSetFromUserInputWithParam(pos,"abortion"));
		assertEquals(11, controller.getFirstWordIndex());
		//param is "blackboard"
		pos = getLetterPosition("blackboard");
		assertEquals(0,testSetFromUserInputWithParam(pos,"blackboard"));
		assertEquals(177, controller.getFirstWordIndex());
		
		//param is "blackboard"
		 pos = 2;
		assertEquals(1,testSetFromUserInputWithParam(pos,"blackboard"));
		assertEquals(0, controller.getFirstWordIndex());
	}
	
	@Test
	public void testStringMatching(){
		//param is (0, "a")
		String[] expected= new String[]{"a.d.", "a.m.","abandon"};
		assertArrayEquals(expected, testSetStringMatchingWithParam(0,"a"));
		
		//param is (1, "a")
		expected= new String[]{"", "",""};
		assertArrayEquals(expected, testSetStringMatchingWithParam(1,"a"));
		
		//param is (2, "cat")
		expected= new String[]{"cat", "catalog","catalyst"};
		assertArrayEquals(expected, testSetStringMatchingWithParam(2,"cat"));
		
		//param is (0, "cat")
		expected= new String[]{"", "",""};
		assertArrayEquals(expected, testSetStringMatchingWithParam(0,"cat"));
		
		//param is (0, "")
		expected= new String[]{"", "",""};
		assertArrayEquals(expected, testSetStringMatchingWithParam(0,""));
		
		//param is (0, null)
		expected= new String[]{"", "",""};		
		assertArrayEquals(expected, testSetStringMatchingWithParam(0,null));
		
		
	}
	
	
	private int testSetFromUserInputWithParam(int letterPsorition,String input){
		return controller.setFromUserInput(letterPsorition, input);
	}
	
	private int getLetterPosition(String input){
		// Figure out which wordList the input belongs to
		int letterPosition = input.charAt(0) - 'a';
		return letterPosition;
	}
	
	private String[] testSetStringMatchingWithParam(int letterPsorition,String input){
		return controller.stringMatching(input, letterPsorition);
	}
	

}
