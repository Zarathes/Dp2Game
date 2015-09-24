package controller.behaviour;

import java.util.ArrayList;

import library.Point;
import model.entity.GameObject;

public class MoveBehaviour {
	
	private ArrayList<GameObject> entities;
	
	public MoveBehaviour(){
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
			
			Point pos = entity.getPosition();
			pos.setX(pos.getX() + (entity.getDirection().getX() / delta));	
			pos.setY(pos.getY() + (entity.getDirection().getY() / delta));			
		}		
	}
}
