package model;

import controller.level.Level;

public class LevelFactory {
	private static LevelFactory instance;
    
    public static LevelFactory getInstance() {
    	if(instance == null) {
    		instance = new LevelFactory();
        }
    	return instance;
    }   
	
	public Level getNextLevel(LevelID id){		
		return id.createLevel();
    }
}
