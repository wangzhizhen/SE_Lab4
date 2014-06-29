/**
 * 
 */
package controller.test;


import java.util.ArrayList;
import java.util.List;

import model.Dictionary;
import model.TxtFileParser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import controller.ChartDataController;

import junit.framework.TestCase;

/**
 * @author Zhizhen
 *
 */
public class TestChartDataController extends TestCase {
 
	private ChartDataController controller;
	private TxtFileParser parser;
	private Dictionary dic;
	@Rule
	public ExpectedException thrown= ExpectedException.none();
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

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		controller = ChartDataController.getInstance();
		parser = TxtFileParser.getInstance();
		parser.init("file/txtfile.txt");
		dic = Dictionary.getInstance();
		
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link controller.ChartDataController#getAllNum(int)}.
	 */
	@Test
	public void testGetAllNum() throws IndexOutOfBoundsException {
		assertEquals(dic.getWordListLengthAt(0), controller.getAllNum(0));
		assertEquals(dic.getWordListLengthAt(5), controller.getAllNum(5));
		thrown.expect(IndexOutOfBoundsException.class);
		thrown.expectMessage("Param should less than 26.");
		assertEquals(0, controller.getAllNum(29));
		
	}

	/**
	 * Test method for {@link controller.ChartDataController#getDoneNum(int)}.
	 */
	@Test
	public void testGetDoneNum() {
		thrown.expect(IndexOutOfBoundsException.class);
		thrown.expectMessage("Param should less than 26.");
		controller.getDoneNum(27);
	}

	/**
	 * Test method for {@link controller.ChartDataController#getCorrectNum(int)}.
	 */
	@Test
	public void testGetCorrectNum() {
		thrown.expect(IndexOutOfBoundsException.class);
		thrown.expectMessage("Param should less than 26.");
		controller.getDoneNum(27);
	}

	/**
	 * Test method for {@link controller.ChartDataController#getCorrectRate(int)}.
	 */
	@Test
	public void testGetCorrectRate() {

	}

}
