package controller.test;

import static org.junit.Assert.*;

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

}
