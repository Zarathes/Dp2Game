package controller;

import java.awt.Graphics;

import controller.behaviour.AnimateBehaviour;
import controller.behaviour.MoveBehaviour;
import controller.behaviour.RenderBehaviour;
import controller.behaviour.UpdateBehaviour;
import model.EntityID;
import model.entity.Astroid;

public class ObjectManager {
	private static ObjectManager instance = null;
	
	private MoveBehaviour moveContainer;
	private UpdateBehaviour updateContainer;
	private AnimateBehaviour animateContainer;
	private RenderBehaviour renderContainer;
	
    protected ObjectManager() {
    	moveContainer = new MoveBehaviour();
    	updateContainer = new UpdateBehaviour();
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
	    		new Astroid(moveContainer, updateContainer, animateContainer, renderContainer);
	    		break;
	    	case SmallShip:
	    		break;
	    	default:
	    		break;
    	}
    }

	public void update(float delta) {
		if(Astroid.needsSpawn()){
			createEntity(EntityID.Astroid);
		}
		
		moveContainer.execute(delta);
		updateContainer.execute(delta);
		animateContainer.execute(delta);
	}     
	
	public void render(Graphics g) {
		renderContainer.execute(g);
	}   
}
