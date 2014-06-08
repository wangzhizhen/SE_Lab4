package controller;

public class SaveToTxtControllerFactory implements ISaveControllerFactory{
	
	public ISaveController getSaveController() {
		// TODO Auto-generated method stub
		return new SaveToTxtController();
	}
}
