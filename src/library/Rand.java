package library;

import java.util.Random;

public class Rand {

    private static Rand instance = null;

    protected Rand() {
        // Exists only to defeat instantiation.
    }

    public static Rand getInstance() {
        if (instance == null) {
            instance = new Rand();
        }
        return instance;
    }
    
    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
    public static float randFloat(float min, float max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        float randomNum = rand.nextFloat() * (max - min) + min;

        return randomNum;
    }
}