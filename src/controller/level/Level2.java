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

public class Level2 extends Level {
	private float SPAWN_TIMER = 75, currentTimer = SPAWN_TIMER, levelDuration = 1500;	
	
	private int score = 0;

	public Level2(){
		levelId = LevelID.Level2;	
		currentState = LevelState.Running;
		levelNumber = 2;
		maxAstroids = 10;
		maxShips = 5;
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

		levelDuration = levelDuration - delta;
		
		if(levelDuration < 0){
			if(score < 1000){
				currentState = LevelState.GameOver;
			}
		}
		
		if(score >= 1000){
			currentState = LevelState.Finished;
		}
		
	}     

	@Override
	public void render(Graphics g) {
		g.drawImage(ResourceManager.getInstance().getSprite("background2"), 0, 0, null);
		
		renderContainer.execute(g);

        g.setColor(Color.red);
		if(currentState == LevelState.GameOver){
			Font gameDefault = g.getFont();
			Font font = new Font(Font.SERIF, Font.BOLD, 30);
			g.setFont(font);
			FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
	        int msgLength = fontMetrics.stringWidth("Game Over");
	        int msgHeight = fontMetrics.getAscent();
	        g.drawString("Game Over", (int) (GWIDTH / 2) - (msgLength / 2), (GHEIGHT / 2));

	        msgLength = fontMetrics.stringWidth("SCORE: " + score);
	        g.drawString("SCORE: " + score, (int) (GWIDTH / 2) - (msgLength / 2), ((GHEIGHT / 2) + msgHeight));	        

	        msgLength = fontMetrics.stringWidth("Press Q to quit");
	        g.drawString("Press Q to quit", (int) (GWIDTH / 2) - (msgLength / 2), ((GHEIGHT / 2) + msgHeight * 2));

			g.setFont(gameDefault);
		}else {
			g.drawString("Level " + levelNumber, 10, 10);
			g.drawString("Score " + score, 10, 20);
			int seconds = (int) (levelDuration / 100) % 60 ;
			g.drawString("Time remaining " + seconds, 10, 30);
		}
	}
	
	public void addScore(int points){
		score += points;
	}
}
