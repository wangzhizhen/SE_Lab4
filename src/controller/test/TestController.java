/**
 * 
 */
package controller.test;


import java.util.ArrayList;
import java.util.List;

import model.TxtFileParser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import controller.Controller;
import controller.FirstWordSettingController;

import junit.framework.TestCase;

/**
 * @author Zhizhen
 *
 */
public class TestController extends TestCase {

    private Controller controller;
    
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		controller = new Controller();
		TxtFileParser.getInstance().init("file/txtfile.txt");
	}

	/**
	 * Test method for {@link controller.Controller#setFirstWordIndex(int, int, java.lang.String)}.
	 */
	@Test
	public void testSetFirstWordIndex() {
		//set firstword from first word in the dictionary
		assertEquals(0,controller.setFirstWordIndex(1, 1, "") );
		assertEquals(0, controller.getFirstWordIndex());
		
		//set firstword from last time index
		assertEquals(-1,controller.setFirstWordIndex(2, 1, "") );
		assertEquals(0, controller.getFirstWordIndex());
		
		//set firstword from user input
		assertEquals(0,controller.setFirstWordIndex(3, 0, "abide") );
		assertEquals(4, controller.getFirstWordIndex());
		
		//set firstword from user input
		assertEquals(1,controller.setFirstWordIndex(3, 0, "abi") );
		assertEquals(0, controller.getFirstWordIndex());
	}
	
	/**
	 * Test method for {@link controller.Controller#setNum(java.lang.String)}.
	 */
	@Test
	public void testSetNum() {
		//invalid input
		assertEquals(2, controller.setNum(""));
		assertEquals(0, controller.getNum());
		
		//invalid input
		assertEquals(2, controller.setNum("abcd"));
		assertEquals(0, controller.getNum());
		
		// input num is minor
		assertEquals(2, controller.setNum("-1"));
		assertEquals(0, controller.getNum());
		
		//input num is bigger
		assertEquals(1, controller.setNum("1000"));
		assertEquals(0, controller.getNum());
	}

	/**
	 * Test method for {@link controller.Controller#normalQuizRun(java.lang.String)}.
	 */
	@Test
	public void testNormalQuizRun() throws IndexOutOfBoundsException{
		//mock the process of a game, from start to end.
		//One recite all the word correspondence to the num he set
		//一种运行场景，背完他设定的数目的单词
		controller.normalQuizStart(0, 0, 5);
		String nextChinese = controller.normalQuizRun("abandon");
		assertEquals("n.放弃", nextChinese);
		nextChinese = controller.normalQuizRun("abay");
		controller.normalQuizRun("abay");
		controller.normalQuizRun("abay");
		nextChinese = controller.normalQuizRun("abay");
		assertEquals(null, nextChinese);
		assertEquals("abide", controller.getPreviousWordEnglish());
		
		 thrown.expect(IndexOutOfBoundsException.class);
		 thrown.expectMessage("Recite all the word, game is over.");
		 controller.normalQuizRun("abay");
		 assertEquals(0.2, controller.getCurrentQuizCorrectRate());
		

	}

	/**
	 * Test method for {@link controller.Controller#normalQuizStop()}.
	 */
	@Test
	public void testNormalQuizStop() {
		
	}

	/**
	 * Test method for {@link controller.Controller#getCurrentQuizCorrectRate()}.
	 */
	@Test
	public void testGetCurrentQuizCorrectRate() {

	}

}
