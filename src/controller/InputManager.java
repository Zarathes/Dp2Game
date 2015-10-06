package controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.PriorityQueue;

import library.KeyPress;
import library.MouseClick;

/**
 * @author Bart Schut
 */
public class InputManager{
        
    private PriorityQueue<MouseClick> clicks = null;
    private PriorityQueue<library.KeyPress> presses = null;
    
    public InputManager() {        
        clicks = new PriorityQueue<MouseClick>();
        presses = new PriorityQueue<library.KeyPress>();
    }
    
    public void processMouseClick(MouseEvent me) {
    	clicks.add(new MouseClick(me.getX(), me.getY(), me.getButton()));
    }

    public void processKeyInput(KeyEvent e) {
        presses.add(new library.KeyPress(e.getKeyCode(), e));
    }

	public PriorityQueue<MouseClick> getMouseClicks() {
		PriorityQueue<MouseClick> tempClicks = clicks;
		clicks = new PriorityQueue<MouseClick>();
		return tempClicks;
	}

	public PriorityQueue<KeyPress> getKeyPresses() {
		PriorityQueue<KeyPress> tempPresses = presses;
		presses = new PriorityQueue<KeyPress>();
		return tempPresses;
	}
}