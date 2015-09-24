package library;

import java.util.Random;

public class RandInt {

    private static RandInt instance = null;

    protected RandInt() {
        // Exists only to defeat instantiation.
    }

    public static RandInt getInstance() {
        if (instance == null) {
            instance = new RandInt();
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
}