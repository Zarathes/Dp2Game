package controller.level;

import java.awt.Graphics;
import java.util.PriorityQueue;

import controller.behaviour.CollisionBehaviour;
import controller.behaviour.MoveBehaviour;
import controller.behaviour.RenderBehaviour;
import controller.behaviour.TouchBehaviour;
import controller.behaviour.UpdateBehaviour;
import library.KeyPress;
import library.MouseClick;
import model.LevelID;
import model.LevelState;

public abstract class Level {	
	protected TouchBehaviour touchContainer;
	protected MoveBehaviour moveContainer;
	protected UpdateBehaviour updateContainer;
	protected CollisionBehaviour collisionContainer;
	protected RenderBehaviour renderContainer;	
    
	protected LevelID levelId;
	protected int levelNumber;
	
	protected LevelState currentState;
	
	protected boolean finished = false;
	protected int maxAstroids = 0, maxShips = 0;
	
    protected Level() {
    	touchContainer = new TouchBehaviour();
    	moveContainer = new MoveBehaviour();
    	updateContainer = new UpdateBehaviour();
    	collisionContainer = new CollisionBehaviour();
    	renderContainer = new RenderBehaviour();
    }
	
	public LevelID getLevelId(){ return levelId; }
	
	public int getLevelNumber(){ return levelNumber; }
	
	public LevelState getState(){ return currentState; }

	public TouchBehaviour getTouchContainer() {
		return touchContainer;
	}

	public MoveBehaviour getMoveContainer() {
		return moveContainer;
	}

	public UpdateBehaviour getUpdateContainer() {
		return updateContainer;
	}

	public CollisionBehaviour getCollisionContainer() {
		return collisionContainer;
	}

	public RenderBehaviour getRenderContainer() {
		return renderContainer;
	}

	public abstract void update(float delta, PriorityQueue<KeyPress> processKeyInput,
			PriorityQueue<MouseClick> processMouseInput);

	public abstract void render(Graphics g);
	
	public int getMaxAstroids() {
		return maxAstroids;
	}

	public int getMaxShips() {
		return maxShips;
	}

	public void clean() {
		touchContainer.clean();
    	moveContainer.clean();
		updateContainer.clean();
    	collisionContainer.clean();
    	renderContainer.clean();
	}

	public abstract void addScore(int worth);
}