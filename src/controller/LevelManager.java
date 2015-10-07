package controller;

import controller.level.Level;
import model.LevelFactory;
import model.LevelID;
import model.LevelState;

public class LevelManager {
	private static LevelManager instance;
    
    public static LevelManager getInstance() {
    	if(instance == null) {
    		instance = new LevelManager();
        }
    	return instance;
    }    
    
    private Level currentLevel = null;
    
	public Level currentLevel() {
		if(currentLevel == null || currentLevel.getState() == LevelState.Finished){			
			return nextLevel();
		}
		
		return currentLevel;
	} 
	
	public Level nextLevel() {
		if(currentLevel == null){
			currentLevel = LevelFactory.getInstance().getNextLevel(LevelID.Init);
		} else if(currentLevel.getState() == LevelState.Finished){
			currentLevel.clean();
			currentLevel = LevelFactory.getInstance().getNextLevel(currentLevel.getLevelId());
		}
		
		return currentLevel;
	}   
}
