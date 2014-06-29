package controller;

public class SaveForXmlControllerFactory {
	public ISaveController getSaveController(int letterPosition) {
		// TODO Auto-generated method stub
		return new SaveForXmlController(letterPosition);
	}
}
