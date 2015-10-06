package controller;

import static model.GameGlobals.SPAWN_TIMER;

import java.awt.Graphics;
import java.util.PriorityQueue;

import library.KeyPress;
import library.MouseClick;
import model.EntityID;
import model.entity.Astroid;
import model.entity.SmallDrone;

public class LevelManager {
	private static LevelManager instance;
    
    public static LevelManager getInstance() {
    	if(instance == null) {
    		instance = new LevelManager();
        }
    	return instance;
    }
    
    private int level = 1;
    
	public void update(float delta, PriorityQueue<KeyPress> priorityQueue, PriorityQueue<MouseClick> mouseClicks) {
		
	}     
	
	public void render(Graphics g) {
		renderContainer.execute(g);
	}   
}
