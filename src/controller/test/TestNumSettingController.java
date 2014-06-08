package controller.test;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import model.Dictionary;
import model.TxtFileParser;

import controller.NumSettingController;


import junit.framework.*;
import org.jmock.*;


public class TestNumSettingController extends TestCase {

	private NumSettingController numSettingController;
	Dictionary dictionary;
      
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		numSettingController = NumSettingController.getInstance();
		dictionary = Dictionary.getInstance();
		TxtFileParser.getInstance().init("file/txtfile.txt");
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetInstance() {
		

	}

	@Test
	public void testSetNumAndGetNum() {
		//num is less than the maxNum
		numSettingController.getMaxNum(0, 0);
		testSetNumWithParam(0, 50, 50);
		
		//num is bigger than the maxNum
		numSettingController.getMaxNum(0, 50);
		testSetNumWithParam(1, 512, 570);
		
		//num is equal to the maxNum
		numSettingController.getMaxNum(0, 0);
		testSetNumWithParam(0, 562, 562);
		
		//num is minor
		numSettingController.getMaxNum(0, 0);
		testSetNumWithParam(0, -1, -1);
	}

	@Test
	public void testGetMaxNum() {		
		int actual = numSettingController.getMaxNum(0, 10);
		assertEquals(552, actual);				
		System.out.println(actual);
		actual = numSettingController.getMaxNum(0, 0);
		assertEquals(562, actual);				
		System.out.println(actual);
	}

	private void testSetNumWithParam(int expectedState,int expectedVal,int num){
	    int actualState = numSettingController.setNum(num);
		assertEquals(expectedState, actualState);
		int actualVal = numSettingController.getNum();
		assertEquals(expectedVal, actualVal);
	}
}
