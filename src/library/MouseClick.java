package library;

/**
 * @author Bart Schut
 */
public class MouseClick implements Comparable<MouseClick> {

    private int x, y, button;

    public MouseClick(int x, int y, int b) {
        this.x = x;
        this.y = y;
        this.button = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getButton() {
        return button;
    }

	public int compareTo(MouseClick o) {
		// TODO Auto-generated method stub
		return 0;
	}
}