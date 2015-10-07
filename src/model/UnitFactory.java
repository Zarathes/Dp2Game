package model;

import controller.level.Level;

public class UnitFactory {
	private static UnitFactory instance;
    
    public static UnitFactory getInstance() {
    	if(instance == null) {
    		instance = new UnitFactory();
        }
    	return instance;
    }   
	
	public void createEntity(EntityID id, Level currentLevel){		
		id.createEntity(currentLevel);
    }
}
