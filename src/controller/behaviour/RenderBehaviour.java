package controller.behaviour;

import static model.GameGlobals.DEBUG;

import java.awt.Color;
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
						
			g.drawImage(entity.getSprite(), (int)(entity.getPosition().getX() - (entity.getSize().getWidth() / 2)), (int)(entity.getPosition().getY() - (entity.getSize().getHeight() / 2)), (int)entity.getSize().getWidth(), (int)entity.getSize().getHeight(), null);

			if(DEBUG){
				g.setColor(Color.white);
				g.drawLine((int)entity.getOrigin().getX(), (int)entity.getOrigin().getY(), (int)entity.getPosition().getX(), (int)entity.getPosition().getY());
				g.drawOval((int)(entity.getPosition().getX() - (entity.getSize().getWidth() / 2) * 0.8), (int)((entity.getPosition().getY() - (entity.getSize().getHeight() / 2) * 0.8)), (int)(entity.getSize().getWidth() * 0.8), (int)(entity.getSize().getHeight() * 0.8));
			}
		}		
	}
}