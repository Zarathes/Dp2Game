package library;

import java.awt.event.KeyEvent;

/**
 * @author Bart Schut
 */
public class KeyPress implements Comparable<KeyPress>{

    private int keyCode;
    private KeyEvent keyEvent;

    public KeyPress(int k, KeyEvent e){
        this.keyCode = k;
        this.keyEvent = e;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public KeyEvent getKeyEvent() {
        return keyEvent;
    }

	public int compareTo(KeyPress arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}