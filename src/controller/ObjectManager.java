package controller;

import static model.GameGlobals.SPAWN_TIMER;

import java.awt.Graphics;
import java.util.PriorityQueue;

import controller.behaviour.AnimateBehaviour;
import controller.behaviour.CollisionBehaviour;
import controller.behaviour.MoveBehaviour;
import controller.behaviour.RenderBehaviour;
import controller.behaviour.TouchBehaviour;
import controller.behaviour.UpdateBehaviour;
import library.KeyPress;
import library.MouseClick;
import model.EntityID;
import model.entity.Astroid;
import model.entity.SmallDrone;

public class ObjectManager {
	private float timer = SPAWN_TIMER;		
	
	private TouchBehaviour touchContainer;
	private MoveBehaviour moveContainer;
	private UpdateBehaviour updateContainer;
	private CollisionBehaviour collisionContainer;
	private AnimateBehaviour animateContainer;
	private RenderBehaviour renderContainer;
	
    protected ObjectManager() {
    	touchContainer = new TouchBehaviour();
    	moveContainer = new MoveBehaviour();
    	updateContainer = new UpdateBehaviour();
    	collisionContainer = new CollisionBehaviour();
    	animateContainer = new AnimateBehaviour();
    	renderContainer = new RenderBehaviour();
    }
    
    public void createEntity(EntityID id){
    	switch(id){
	    	case Player:
	    		break;
	    	case Astroid:
	    		new Astroid(moveContainer, updateContainer, collisionContainer, animateContainer, renderContainer);
	    		break;
	    	case SmallDrone:
	    		new SmallDrone(touchContainer, moveContainer, updateContainer, collisionContainer, animateContainer, renderContainer);
	    		break;
	    	default:
	    		break;
    	}
    }

	public void update(float delta, PriorityQueue<KeyPress> priorityQueue, PriorityQueue<MouseClick> mouseClicks) {
		if(Astroid.needsSpawn() && timer < 0){
			createEntity(EntityID.Astroid);
			timer = SPAWN_TIMER;
		} else {
			timer = timer - delta;
		}
		
		if(SmallDrone.needsSpawn()){
			createEntity(EntityID.SmallDrone);
		}
		
		touchContainer.execute(mouseClicks);
		moveContainer.execute(delta);
		updateContainer.execute(delta);
		collisionContainer.execute();
		animateContainer.execute(delta);
	}     
	
	public void render(Graphics g) {
		renderContainer.execute(g);
	}   
}
