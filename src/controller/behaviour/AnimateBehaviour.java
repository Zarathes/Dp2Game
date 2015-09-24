package controller.behaviour;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.ArrayList;

import model.EntityID;
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
				GameObject entity = entities.get(i);	
				
				if(entity.getId() == EntityID.Astroid){				
					double rotationRequired = Math.toRadians(5);
					AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, entity.getSprite().getWidth() / 2, entity.getSprite().getWidth() / 2);
					AffineTransformOp ato = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					entity.setSprite(ato.filter(entity.getSprite(), null));
				}				
			}	
			timer = 1f;
		} else{
			timer = timer - delta;
		}	
	}
}
