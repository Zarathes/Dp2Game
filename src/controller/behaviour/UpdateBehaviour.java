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
			
			if(-50 > entity.getPosition().getX() || entity.getPosition().getX() > GWIDTH + 50 || 
			   -50 > entity.getPosition().getY() || entity.getPosition().getY() > GHEIGHT + 50 ){
				entity.setAlive(false);
			}
			
			if(entity.isAlive() == false){
				entity.die();
			}
		}			
	}
}
