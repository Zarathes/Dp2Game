package controller;

import static model.GameGlobals.DEBUG;
import static model.GameGlobals.FULLSCREEN;
import static model.GameGlobals.GHEIGHT;
import static model.GameGlobals.GWIDTH;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.PriorityQueue;

import library.KeyPress;
import library.MouseClick;
import model.LevelState;
import model.GameGlobals.States;
import view.GameWindow;

public class GameController extends Canvas implements Runnable {

	private static final long serialVersionUID = 6178913904587781358L;
	
	private Thread gameLoop;
	private boolean running = false;
	private States gameState = null;
	private InputManager im = null;
	
	public GameController(){
		if(FULLSCREEN){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			GWIDTH = (int) screenSize.getWidth();
			GHEIGHT = (int) screenSize.getHeight();
		}		
		new GameWindow("Space Shooter", this); 
		
		this.addMouseListener(new MouseAdapter() {
	    	@Override
	        public void mouseClicked(MouseEvent me){
	        	im.processMouseClick(me);
	         	me.consume();
	       }
		});
	        
	    this.addKeyListener(new KeyAdapter() {
	    	@Override
	        public void keyPressed(KeyEvent e) {
	    		im.processKeyInput(e);
	            e.consume();
	        }
	    }); 
	    
	    this.requestFocus();
	}
	
	public synchronized void start(){
		gameLoop = new Thread(this);
		gameLoop.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			System.exit(0);
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
		
		im =  new InputManager();
		gameState = States.RUNNING;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(gameState == States.RUNNING){		
				while(delta >= 1){
					gameUpdate(delta);
					delta--;
				}
			} else {
				processKeyInput();
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
	
	private PriorityQueue<KeyPress> processKeyInput() {
		PriorityQueue<KeyPress> presses = im.getKeyPresses();
		PriorityQueue<KeyPress> unusedPresses = new PriorityQueue<>();		
		
		while(presses.peek() != null){
			KeyPress temp = presses.remove();
			switch (temp.getKeyCode()) {
				case KeyEvent.VK_P:
					if(gameState == States.PAUSED){
						gameState = States.RUNNING;
					} else if(gameState == States.RUNNING){
						gameState = States.PAUSED;						
					}
					break;
				case KeyEvent.VK_ESCAPE:
			    case KeyEvent.VK_Q:
			    	stop();
			        break;
			    case KeyEvent.VK_D:
			    	DEBUG = !DEBUG;
			    	break;			   
			    default:
			    	unusedPresses.add(temp);
			    	break;
			  }
		}
		
		return unusedPresses;
	}
	
	private PriorityQueue<MouseClick> processMouseInput(){
		return im.getMouseClicks();
	}

	private void gameUpdate(float delta) {
		if(LevelManager.getInstance().currentLevel().getState() != LevelState.GameOver){
			LevelManager.getInstance().currentLevel().update(delta, processKeyInput(), processMouseInput());
		} else {
			processKeyInput();
		}
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
		
		LevelManager.getInstance().currentLevel().render(g);
				
		g.dispose();
		bs.show();
		
	}

	public States getGameState() {
		return gameState;
	}
}
