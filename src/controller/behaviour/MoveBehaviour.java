package controller.behaviour;

import java.util.ArrayList;

import library.Vector;
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
			
			entity.setPosition(entity.getPosition().addVector(Vector.divideVector(entity.getDirection(), delta)));		
		}		
	}
}
