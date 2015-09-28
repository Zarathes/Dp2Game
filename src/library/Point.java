package library;

public class Point {
	private float x, y;
	
	public Point(){
		this.x = 0;
		this.y = 0;
	}
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public Point addVector(Vector v){
		Point p2 = new Point();
		
		p2.x = x + v.getX();
		p2.y = y + v.getY();
		
		return p2;
	}
	
	public static Vector subtractPoints(Point target, Point origin){
		Vector v = new Vector();
		
		v.setX(target.x - origin.x);
		v.setY(target.y - origin.y);
		
		return v;
	}
}
