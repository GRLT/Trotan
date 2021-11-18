package util;

import java.util.Random;

public class Util {

    private static final Random rand = new Random();

    public static int randomIntBetweenRange(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

}
