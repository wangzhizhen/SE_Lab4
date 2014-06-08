package controller.test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Action;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.ISaveController;
import controller.SaveControllerFactory;
import controller.test.mock.IDictionary;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestSaveControllerFactory extends TestCase {

	private SaveControllerFactory saveControllerFactory;
	Mockery context = new Mockery();
	final ISaveController saveController = context.mock(ISaveController.class);
	  
	    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		saveControllerFactory = new SaveControllerFactory();
		context.checking(new Expectations(){
			{
				allowing("saveToTxtController");
				will(returnValue(saveController));			
			}

		});
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetSaveController() {
		ISaveController actual = saveControllerFactory.getSaveController("saveToTxtController");
		  
		 Assert.assertEquals(saveController, actual);
		 
		  
	}
	

}
