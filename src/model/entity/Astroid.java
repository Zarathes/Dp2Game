package model.entity;

import static model.GameGlobals.GHEIGHT;
import static model.GameGlobals.GWIDTH;

import java.awt.Dimension;

import controller.behaviour.AnimateBehaviour;
import controller.behaviour.MoveBehaviour;
import controller.behaviour.RenderBehaviour;
import controller.behaviour.UpdateBehaviour;
import library.Point;
import library.Rand;
import library.Vector;
import model.EntityID;
import model.ResourceManager;

public class Astroid extends GameObject {
	private MoveBehaviour moveContainer;
	private UpdateBehaviour updateContainer;
	private AnimateBehaviour animateContainer;
	private RenderBehaviour renderContainer;
	
	private static int spawnCount = 0;
	
	public Astroid(MoveBehaviour mb, UpdateBehaviour ub, AnimateBehaviour ab, RenderBehaviour rb) {
		super(EntityID.Astroid);
		
		init();
		
		moveContainer = mb;
		moveContainer.add(this);
		
		updateContainer = ub;
		updateContainer.add(this);
		
		animateContainer = ab;
		animateContainer.add(this);
		
		renderContainer = rb;
		renderContainer.add(this);
		
		spawnCount++;
	}
	
	private void init(){
		Vector min;
		Vector max;
		
		switch(Rand.randInt(0,3)){
			case 0:
				// North
				// Start position
				setPosition(new Point(Rand.randInt(50, GWIDTH - 50), -50));
				
				// Direction
				min = new Vector((0 - getPosition().getX()) / (GHEIGHT - getPosition().getY()), 1f);
				max = new Vector((GWIDTH - getPosition().getX()) / (GHEIGHT - getPosition().getY()) , 1f);				
				
				setDirection(new Vector(Rand.randFloat(min.getX(), max.getX()), 1));
				break;
			case 1:
				// East
				setPosition(new Point(GWIDTH, Rand.randInt(50,GHEIGHT -50)));
				
				// Direction
				min = new Vector(1f, (0 - getPosition().getY()) / (GWIDTH - getPosition().getX()));
				max = new Vector(1f, (GHEIGHT - getPosition().getY()) / (GHEIGHT - getPosition().getX()));				
				
				setDirection(new Vector(1, Rand.randFloat(min.getY(), max.getY())));
				break;
			case 2:
				// South
				setPosition(new Point(Rand.randInt(50, GWIDTH - 50), GHEIGHT + 50));
				
				// Direction
				min = new Vector((0 - getPosition().getX()) / (getPosition().getY()), -1f);
				max = new Vector((GWIDTH - getPosition().getX()) / (getPosition().getY()) , -1f);				
				
				setDirection(new Vector(Rand.randFloat(min.getX(), max.getX()), -1));
				break;
			default:
				//West
				setPosition(new Point( -50, Rand.randInt(0,GHEIGHT)));
				// Direction
				min = new Vector(1f, (0 - getPosition().getY()) / (getPosition().getX()));
				max = new Vector(1f, (GHEIGHT - getPosition().getY()) / (getPosition().getX()));				
				
				setDirection(new Vector(-1, Rand.randFloat(min.getY(), max.getY())));
				break;
		
		}
		setSprite(ResourceManager.getInstance().getSprite("astroid"));
		setAlive(true);
		int size = Rand.randInt((GWIDTH/ 100) * 5, (GWIDTH / 100) * 15);
		setSize(new Dimension(size, size));
		
		System.out.println("Spawn Astroid");
	}
	
	public static boolean needsSpawn(){
		if(spawnCount < 5){
			return true;
		}
		
		return false;
	}
	
	public void die(){
		System.out.println("Die");
		
		moveContainer.remove(this);
		updateContainer.remove(this);
		animateContainer.remove(this);
		renderContainer.remove(this);
		
		spawnCount--;
	}
}
