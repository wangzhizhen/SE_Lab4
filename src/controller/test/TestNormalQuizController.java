/**
 * Test case for normalquiz controller
 */
package controller.test;


import model.TxtFileParser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import controller.NormalQuizController;

import junit.framework.TestCase;

/**
 * @author Zhizhen
 *
 */
public class TestNormalQuizController extends TestCase {

	private NormalQuizController controller;
	/**
	 * @throws java.lang.Exception
	 */

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		controller = NormalQuizController.getInstance();
		TxtFileParser.getInstance().init("file/txtfile.txt");
	}


	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link controller.NormalQuizController#start()}.
	 */
	@Test
	public void testStart() {
		//start with diffrent value
		testStartWithParam(0, 0, 5, "v.抛弃，放弃");
		testStartWithParam(0, 1, 5, "n.放弃");
		testStartWithParam(1, 3, 6, "n.单身汉，学士");
		testStartWithParam(1, 4, 7, "n.背 adj.背后的");
		
	}

	/**
	 * Test method for {@link controller.NormalQuizController#compare(java.lang.String)}.
	 */
	@Test
	public void testCompare() {
		testStartWithParam(0, 0, 5, "v.抛弃，放弃");
		int actual= controller.compare("abandon");
		assertEquals(2, actual);
		actual= controller.compare("abandonmence");
		assertEquals(1, actual);
	}

	/**
	 * Test method for {@link controller.NormalQuizController#updateQuiz(int)}.
	 */
	@Test
	public void testUpdateQuizAndNext() {
		testStartWithParam(0, 0, 5, "v.抛弃，放弃");	
		int compareResult = controller.compare("abandon");
		controller.updateQuiz(compareResult);
		assertEquals(1, controller.getCurrentWordIndex());
		assertEquals(1, controller.getCurrentQuizCorrectNum());
		assertEquals("n.放弃", controller.next());
		
	}

	/**
	 * Test method for {@link controller.NormalQuizController#getPreviousWordEnglish()}.
	 */
	@Test
	public void testGetPreviousWordEnglish() {
		testUpdateQuizAndNext();
		assertEquals("abandon", controller.getPreviousWordEnglish());
	}

	private void testStartWithParam(int letterPosition,int firstWordIndex, int num, String expectedVal){
		controller.setLetterPosition(letterPosition);
		controller.setFirstWordIndex(firstWordIndex);
		controller.setNum(num);
		String actualVal = controller.start();
		assertEquals(expectedVal, actualVal);
		assertEquals(0, controller.getCurrentWordIndex());
	}
}
