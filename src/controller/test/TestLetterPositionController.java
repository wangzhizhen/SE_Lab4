/**
 * Test case for LetterPositionSettingController;
 */

package controller.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.LetterPositionSettingController;

/**
 * @author Zhizhen
 *
 */
public class TestLetterPositionController extends TestCase {

	/**
	 * Test method for {@link controller.LetterPositionSettingController#getInstance()}.
	 */
	LetterPositionSettingController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = LetterPositionSettingController.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testGetInstance() {
		
		
	}

	/**
	 * Test method for {@link controller.LetterPositionSettingController#getLetterPosition()}.
	 */
	@Test
	public void testGetLetterPosition() {
		int letterPosition  = controller.getLetterPosition();
		assertEquals(1, letterPosition);
	}

	/**
	 * Test method for {@link controller.LetterPositionSettingController#setLetterPosition(int)}.
	 */
	@Test
	public void testSetLetterPosition() {
		
		assertEquals(5, testSetLetterPositionWithParam(5));
		assertEquals(0, testSetLetterPositionWithParam(0));
		assertEquals(-1, testSetLetterPositionWithParam(-1));
		assertEquals(1, testSetLetterPositionWithParam(1));
	}
	
	public int testSetLetterPositionWithParam(int setVal){		
		controller.setLetterPosition(setVal);
		int letterPosition = controller.getLetterPosition();		
		return letterPosition;
	}
	
}
