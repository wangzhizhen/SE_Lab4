package controller;

public class SaveControllerFactory {
	
    public ISaveController getSaveController(String token){
    	ISaveController saveController = null;
    	if(token.equals("saveToTxtController")){
    		saveController = new SaveToTxtController();
    	}
		return saveController;
    }
}
