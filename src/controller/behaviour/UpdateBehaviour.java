package controller.behaviour;

import java.util.ArrayList;
import static model.GameGlobals.GHEIGHT;
import static model.GameGlobals.GWIDTH;

import model.entity.GameObject;

public class UpdateBehaviour {
	
	private ArrayList<GameObject> entities;
	
	public UpdateBehaviour(){
		entities = new ArrayList<GameObject>();
	}
	
	public void add(GameObject entity){
		entities.add(entity);		
	}
	
	public void remove(GameObject entity){
		entities.remove(entity);
	}
	
	public void execute(float delta){
		for (int i = 0; i < entities.size(); i++) {
			GameObject entity = entities.get(i);	
			
			if(-entity.getSize().getWidth() > entity.getPosition().getX() || entity.getPosition().getX() > GWIDTH + entity.getSize().getWidth() || 
			   -entity.getSize().getHeight() > entity.getPosition().getY() || entity.getPosition().getY() > GHEIGHT + entity.getSize().getWidth()){
				entity.setAlive(false);
			}
			
			if(entity.isAlive() == false){
				entity.die();
			}
		}			
	}

	public void clean() {for (
		int i = 0; i < entities.size(); i++) {
			GameObject entity = entities.get(i);
			entity.die();
		}		
	}
}
