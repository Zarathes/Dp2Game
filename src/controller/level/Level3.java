package controller.level;

import static model.GameGlobals.GHEIGHT;
import static model.GameGlobals.GWIDTH;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.PriorityQueue;

import library.KeyPress;
import library.MouseClick;
import model.EntityID;
import model.LevelID;
import model.LevelState;
import model.ResourceManager;
import model.UnitFactory;
import model.entity.Astroid;
import model.entity.SmallDrone;

public class Level3 extends Level {
	private float SPAWN_TIMER = 75, currentTimer = SPAWN_TIMER;	
	
	private int score = 0;

	public Level3(){
		levelId = LevelID.Level3;	
		currentState = LevelState.Running;
		levelNumber = 3;
		maxAstroids = 999;
		maxShips = 999;
	}
	
	@Override
	public void update(float delta, PriorityQueue<KeyPress> priorityQueue, PriorityQueue<MouseClick> mouseClicks) {
		if(Astroid.needsSpawn(maxAstroids) && currentTimer < 0){
			UnitFactory.getInstance().createEntity(EntityID.Astroid, this);
			currentTimer = SPAWN_TIMER;
		} else {
			currentTimer = currentTimer - delta;
		}
		
		if(SmallDrone.needsSpawn(maxShips)){
			UnitFactory.getInstance().createEntity(EntityID.SmallDrone, this);
		}
		
		touchContainer.execute(mouseClicks);
		moveContainer.execute(delta);
		updateContainer.execute(delta);
		collisionContainer.execute();
	}     

	@Override
	public void render(Graphics g) {
		g.drawImage(ResourceManager.getInstance().getSprite("background3"), 0, 0, null);
		
		renderContainer.execute(g);

        g.setColor(Color.red);
        
		Font gameDefault = g.getFont();
		Font font = new Font(Font.SERIF, Font.BOLD, 30);
		g.setFont(font);
		FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
        int msgLength = fontMetrics.stringWidth("Game Finished");
        int msgHeight = fontMetrics.getAscent();
        g.drawString("Game Finished", (int) (GWIDTH / 2) - (msgLength / 2), (GHEIGHT / 2));

        msgLength = fontMetrics.stringWidth("SCORE: " + score);
        g.drawString("SCORE: " + score, (int) (GWIDTH / 2) - (msgLength / 2), ((GHEIGHT / 2) + msgHeight));	        

        msgLength = fontMetrics.stringWidth("Press Q to quit");
        g.drawString("Press Q to quit", (int) (GWIDTH / 2) - (msgLength / 2), ((GHEIGHT / 2) + msgHeight * 2));

		g.setFont(gameDefault);
	
	}
	
	public void addScore(int points){
		score += points;
	}
}
