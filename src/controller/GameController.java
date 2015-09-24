package controller;

import static model.GameGlobals.FULLSCREEN;
import static model.GameGlobals.GHEIGHT;
import static model.GameGlobals.GWIDTH;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import model.ResourceManager;
import view.GameWindow;

public class GameController extends Canvas implements Runnable {

	private static final long serialVersionUID = 6178913904587781358L;
	
	private Thread gameLoop;
	private boolean running = false;
	
	public static void main(String args[]){
		new GameController();
	}
	
	public GameController(){
		if(FULLSCREEN){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			GWIDTH = (int) screenSize.getWidth();
			GHEIGHT = (int) screenSize.getHeight();
		}
		
		new GameWindow("Space Shooter", this);
	}
	
	public synchronized void start(){
		gameLoop = new Thread(this);
		gameLoop.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			gameLoop.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Game Loop
	public void run(){		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		float delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1){
				gameUpdate(delta);
				delta--;
			}
			if(running){
				gameRender();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}			
		}
			
		stop();
	}

	private void gameUpdate(float delta) {
		ObjectManager.getInstance().update(delta);		
	}

	private void gameRender() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GWIDTH, GHEIGHT);
		g.drawImage(ResourceManager.getInstance().getSprite("background"), 0, 0, null);
		
		ObjectManager.getInstance().render(g);
				
		g.dispose();
		bs.show();
		
	}
}
