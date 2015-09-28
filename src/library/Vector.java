package library;

public class Vector {
	private float x, y;
	
	public Vector(){
		this.x = 0f;
		this.y = 0f;
	}
	
	public Vector(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public static Vector addVectors(Vector v, Vector s){
		Vector scaled = new Vector();
		
		scaled.x = v.x + s.x;
		scaled.y = v.y + s.y;
		
		return scaled;
	}
	
	public static Vector multiplyVector(Vector v, Vector s){
		Vector scaled = new Vector();
		
		scaled.x = v.x * s.x;
		scaled.y = v.y * s.y;
		
		return scaled;
	}
	
	public static Vector multiplyVector(Vector v, float s){
		Vector scaled = new Vector();
		
		scaled.x = v.x * s;
		scaled.y = v.y * s;
		
		return scaled;
	}
	
	public static Vector divideVector(Vector v, float s){
		Vector scaled = new Vector();
		
		scaled.x = v.x / s;
		scaled.y = v.y / s;
		
		return scaled;
	}
	
	public static Vector divideVector(Vector v, Vector s){
		Vector scaled = new Vector();
		
		scaled.x = v.x / s.x;
		scaled.y = v.y / s.y;
		
		return scaled;
	}
	
	public float length(){
		float length;
		
		length = (float) Math.sqrt(x*x + y*y);
		
		return length;
	}
	
	public Vector normalized(){
		Vector normalized;
		
		normalized = Vector.divideVector(this, this.length());
		
		return normalized;		
	}
	
	public Vector tangent(){
		Vector tangent = new Vector();
		Vector normalized = normalized();
		
		tangent.x = normalized.y / -1;
		tangent.y = normalized.x;
		
		return tangent;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
}
