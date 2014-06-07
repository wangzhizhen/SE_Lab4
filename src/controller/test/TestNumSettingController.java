package controller.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.NumSettingController;
import controller.test.mock.IDictionary;

import junit.framework.*;
import org.jmock.*;


public class TestNumSettingController extends TestCase {

	private NumSettingController numSettingController;
	Mockery context = new Mockery();
	final IDictionary dictionary = context.mock(IDictionary.class);
      
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
		numSettingController.dictionary = dictionary; 
		context.checking(new Expectations(){
			{
				allowing(dictionary).getWordListLengthAt(0);
				will(returnValue(100));			
			}
		});
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
		System.out.println(numSettingController.getMaxNum(0, 10));
		int val1 = numSettingController.setNum(0);
		assertEquals(0, val1);
		assertEquals(0,numSettingController.getNum());
		int val2 = numSettingController.setNum(50);
		assertEquals(0, val2);
		assertEquals(50,numSettingController.getNum());
		int val3 = numSettingController.setNum(100);
		assertEquals(1, val3);
		assertEquals(90,numSettingController.getNum());
	}

	@Test
	public void testGetMaxNum() {
		
		int actual = numSettingController.getMaxNum(0, 10);
		assertEquals(90, actual);		
		assertGetWordListLengthAt(0,100);
		System.out.println(actual);

	}

	
	private void assertGetWordListLengthAt(int letterPosition, int expected) {
		// TODO Auto-generated method stub
		int actual = dictionary.getWordListLengthAt(letterPosition);
		Assert.assertEquals(expected, actual);
	}

}
