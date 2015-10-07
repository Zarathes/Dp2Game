package model.entity;

import static model.GameGlobals.GHEIGHT;
import static model.GameGlobals.GWIDTH;

import java.awt.Dimension;

import controller.behaviour.CollisionBehaviour;
import controller.behaviour.MoveBehaviour;
import controller.behaviour.RenderBehaviour;
import controller.behaviour.UpdateBehaviour;
import controller.level.Level;
import library.Point;
import library.Rand;
import library.Vector;
import model.EntityID;
import model.ResourceManager;

public class Astroid extends GameObject {
	private MoveBehaviour moveContainer;
	private UpdateBehaviour updateContainer;
	private CollisionBehaviour collisionContainer;
	private RenderBehaviour renderContainer;
	
	private static int spawnCount = 0;
	private Level currentLevel;
	
	public Astroid(Level host) {
		super(EntityID.Astroid);
		
		currentLevel = host;
		
		moveContainer = currentLevel.getMoveContainer();
		moveContainer.add(this);
		
		updateContainer = currentLevel.getUpdateContainer();
		updateContainer.add(this);
		
		collisionContainer = currentLevel.getCollisionContainer();
		collisionContainer.add(this);
				
		renderContainer = currentLevel.getRenderContainer();
		renderContainer.add(this);
	
		init();
		
		spawnCount++;
	}
	
	private void init(){
		int size = Rand.randInt((GWIDTH/ 100) * 5, (GWIDTH / 100) * 10);
		setSize(new Dimension(size, size));
		
		Vector min;
		Vector max;
		
		switch(Rand.randInt(0,3)){
			case 0:
				// North
				// Start position
				setPosition(new Point(Rand.randInt(size, GWIDTH - size), -size));
				
				// Direction
				min = new Vector((0 - getPosition().getX()) / (GHEIGHT - getPosition().getY()), 1f);
				max = new Vector((GWIDTH - getPosition().getX()) / (GHEIGHT - getPosition().getY()) , 1f);				
				
				setDirection(new Vector(Rand.randFloat(min.getX(), max.getX()), 1f));
				break;
			case 1:
				// East
				setPosition(new Point(GWIDTH + size, Rand.randInt(size, GHEIGHT -size)));
				
				// Direction
				min = new Vector(1f, (0 - getPosition().getY()) / (0 - getPosition().getX()));
				max = new Vector(1f, (GHEIGHT - getPosition().getY()) / (0 - getPosition().getX()));				
				
				setDirection(new Vector(-1f, Rand.randFloat(min.getY(), max.getY())));
				break;
			case 2:
				// South
				setPosition(new Point(Rand.randInt(size, GWIDTH - size), GHEIGHT + size));
				
				// Direction
				min = new Vector((0 - getPosition().getX()) / (getPosition().getY()), -1f);
				max = new Vector((GWIDTH - getPosition().getX()) / (getPosition().getY()) , -1f);				
				
				setDirection(new Vector(Rand.randFloat(min.getX(), max.getX()), -1));
				break;
			case 3:
				//West
				setPosition(new Point(-size, Rand.randInt(size, GHEIGHT - size)));
				// Direction
				min = new Vector(1f, (0 - getPosition().getY()) / (GWIDTH - getPosition().getX()));
				max = new Vector(1f, (GHEIGHT - getPosition().getY()) / (GWIDTH - getPosition().getX()));				
				
				setDirection(new Vector(1f, Rand.randFloat(min.getY(), max.getY())));
				break;
				default:
					break;
		
		}
		
		setDirection(Vector.multiplyVector(getDirection(), Rand.randFloat(2, 10)));
		
		setSprite(ResourceManager.getInstance().getSprite("astroid" + currentLevel.getLevelNumber()));
		setAlive(true);
	}
	
	public static boolean needsSpawn(int maxAstroids){
		if(spawnCount < maxAstroids){
			return true;
		}
		
		return false;
	}
	
	public void die(){		
		moveContainer.remove(this);
		updateContainer.remove(this);
		collisionContainer.remove(this);
		renderContainer.remove(this);
		
		spawnCount--;
	}
}
