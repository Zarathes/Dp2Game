package model;

public class GameGlobals {

	public final static boolean DEBUG = false;
	public final static boolean FULLSCREEN = true;
	public static int GWIDTH = 640, GHEIGHT = GWIDTH / 12 *9;
	
	public static enum Compass { NORTH, EAST, SOUTH, WEST };
	
	public static int MAX_ASTROID = 10;
	public static int MAX_DRONE = 15;
	public static int SPAWN_TIMER = 100;
}
