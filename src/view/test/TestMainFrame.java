/**
 * 
 */
package view.test;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;

import controller.Controller;
import view.MainFrame;
import view.WordPanel;


/**
 * @author qujiaqing
 *
 */
public class TestMainFrame extends TestCase{
	Mockery context = new Mockery();
	
	//set up
	final Controller controller= context.mock(Controller.class);
	MainFrame frame = new MainFrame();
	
	@Test
	public void testChoosePanelPerformance(){
		//expectation
		 context.checking(new Expectations() {   
		        {   
		        		allowing(controller).setFirstWordIndex(2,0, "");
		        		will(returnValue(1));
		        		allowing(controller).setFirstWordIndex(2,1, "");
		        		will(returnValue(0));
		        }   
		    });
		
		 //
		
	}

}
