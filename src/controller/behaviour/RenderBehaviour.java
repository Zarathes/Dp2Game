package controller.behaviour;

import java.awt.Graphics;
import java.util.ArrayList;

import model.entity.GameObject;

public class RenderBehaviour {
	private ArrayList<GameObject> entities;
	
	public RenderBehaviour(){
		entities = new ArrayList<GameObject>();
	}
	
	public void add(GameObject entity){
		entities.add(entity);		
	}
	
	public void remove(GameObject entity){
		entities.remove(entity);
	}
	
	public void execute(Graphics g){		
		for (int i = 0; i < entities.size(); i++) {
			GameObject entity = entities.get(i);	
			
			g.drawImage(entity.getSprite(), (int)entity.getPosition().getX(), (int)entity.getPosition().getY(), (int)entity.getSize().getWidth(), (int)entity.getSize().getHeight(), null);
			
		}		
	}
}