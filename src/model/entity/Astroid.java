package model.entity;

import static model.GameGlobals.GWIDTH;

import java.awt.Dimension;

import static model.GameGlobals.GHEIGHT;

import controller.behaviour.AnimateBehaviour;
import controller.behaviour.MoveBehaviour;
import controller.behaviour.RenderBehaviour;
import controller.behaviour.UpdateBehaviour;
import library.Point;
import library.RandInt;
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
		switch(RandInt.randInt(0,3)){
			case 0:
				// North
				setPosition(new Point(RandInt.randInt(0,GWIDTH), -50));
				setDirection(new Vector(0, 5));
				break;
			case 1:
				// East
				setPosition(new Point(GWIDTH + 50, RandInt.randInt(0,GHEIGHT)));
				setDirection(new Vector(-5, 0));
				break;
			case 2:
				// South
				setPosition(new Point(RandInt.randInt(0,GWIDTH), GHEIGHT + 50));
				setDirection(new Vector(0, -5));
				break;
			default:
				//West
				setPosition(new Point( -50, RandInt.randInt(0,GHEIGHT)));
				setDirection(new Vector(5, 0));
				break;
		
		}
		setSprite(ResourceManager.getInstance().getSprite("astroid"));
		setAlive(true);
		int size = RandInt.randInt((GWIDTH/ 100) * 5, (GWIDTH / 100) * 15);
		setSize(new Dimension(size, size));
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
