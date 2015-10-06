package library;

/**
 * @author Bart Schut
 */
public class MouseClick implements Comparable<MouseClick> {

    private int button;
    private Point position;

    public MouseClick(int x, int y, int b) {
    	position = new Point(x, y);
        this.button = b;
    }

    public Point getPosition() {
        return position;
    }
    
    public int getButton() {
        return button;
    }

	public int compareTo(MouseClick o) {
		// TODO Auto-generated method stub
		return 0;
	}
}