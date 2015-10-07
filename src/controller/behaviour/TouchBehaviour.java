package controller.behaviour;

import java.util.ArrayList;
import java.util.PriorityQueue;

import library.MouseClick;
import library.Point;
import model.entity.GameObject;

public class TouchBehaviour {
	private ArrayList<GameObject> entities;
	
	public TouchBehaviour(){
		entities = new ArrayList<GameObject>();
	}
	
	public void add(GameObject entity){
		entities.add(entity);		
	}
	
	public void remove(GameObject entity){
		entities.remove(entity);
	}
	
	public void execute(PriorityQueue<MouseClick> mouseClicks){		
		while(mouseClicks.peek() != null){
			Point current = mouseClicks.poll().getPosition();
		
			for (int i = 0; i < entities.size(); i++) {
				GameObject entity = entities.get(i);

				float length = Point.subtractPoints(entity.getPosition(), current).length();
								
				if(length < ((0.5) + (entity.getRadius() * 0.8))){
					entity.setWorth(50);
					entity.setAlive(false);
					break;
				}				
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
