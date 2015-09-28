package controller;

import static model.GameGlobals.SPAWN_TIMER;

import java.awt.Graphics;

import controller.behaviour.AnimateBehaviour;
import controller.behaviour.CollisionBehaviour;
import controller.behaviour.MoveBehaviour;
import controller.behaviour.RenderBehaviour;
import controller.behaviour.UpdateBehaviour;
import model.EntityID;
import model.entity.Astroid;
import model.entity.SmallDrone;

public class ObjectManager {
	private static ObjectManager instance = null;

	private float timer = SPAWN_TIMER;		
	
	private MoveBehaviour moveContainer;
	private UpdateBehaviour updateContainer;
	private CollisionBehaviour collisionContainer;
	private AnimateBehaviour animateContainer;
	private RenderBehaviour renderContainer;
	
    protected ObjectManager() {
    	moveContainer = new MoveBehaviour();
    	updateContainer = new UpdateBehaviour();
    	collisionContainer = new CollisionBehaviour();
    	animateContainer = new AnimateBehaviour();
    	renderContainer = new RenderBehaviour();
    }
    
    public static ObjectManager getInstance() {
    	if(instance == null) {
    		instance = new ObjectManager();
        }
    	return instance;
    }
    
    public void createEntity(EntityID id){
    	switch(id){
	    	case Player:
	    		break;
	    	case Astroid:
	    		new Astroid(moveContainer, updateContainer, collisionContainer, animateContainer, renderContainer);
	    		break;
	    	case SmallDrone:
	    		new SmallDrone(moveContainer, updateContainer, collisionContainer, animateContainer, renderContainer);
	    		break;
	    	default:
	    		break;
    	}
    }

	public void update(float delta) {
		if(Astroid.needsSpawn() && timer < 0){
			createEntity(EntityID.Astroid);
			timer = SPAWN_TIMER;
		} else {
			timer = timer - delta;
		}
		
		if(SmallDrone.needsSpawn()){
			createEntity(EntityID.SmallDrone);
		}
		
		moveContainer.execute(delta);
		updateContainer.execute(delta);
		collisionContainer.execute();
		animateContainer.execute(delta);
	}     
	
	public void render(Graphics g) {
		renderContainer.execute(g);
	}   
}
