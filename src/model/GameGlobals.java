package model;

public class GameGlobals {
	public final static boolean FULLSCREEN = true;
	public static int GWIDTH = 640, GHEIGHT = GWIDTH / 12 *9;

	public static boolean DEBUG = false;
	
	public static enum Compass { NORTH, EAST, SOUTH, WEST };
	
	public static int MAX_ASTROID = 5;
	public static int MAX_DRONE = 10;
	public static int SPAWN_TIMER = 100;
	
    public static enum States{
        NOT_STARTED,
        RUNNING,
        PAUSED,
        GAMEOVER,
        QUIT;
    }
}
