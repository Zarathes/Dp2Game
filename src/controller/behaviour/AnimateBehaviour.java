package controller.behaviour;

import java.util.ArrayList;

import model.entity.GameObject;

public class AnimateBehaviour {
	private float timer;
	private ArrayList<GameObject> entities;
	
	public AnimateBehaviour(){
		timer = 1f;
		entities = new ArrayList<GameObject>();
	}
	
	public void add(GameObject entity){
		entities.add(entity);		
	}
	
	public void remove(GameObject entity){
		entities.remove(entity);
	}
	
	public void execute(float delta){	
		if(timer < 0){
			for (int i = 0; i < entities.size(); i++) {
//				GameObject entity = entities.get(i);
			
			}	
			timer = 1f;
		} else{
			timer = timer - delta;
		}	
	}
}
