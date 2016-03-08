package utilities;

/**
 * Created by aseber on 2/24/16.
 */
public final class MathUtilities {

    public static int putInRange(int min, int current, int max) {

        return Math.min(Math.max(min, current), max); /// This function returns  a number between [min, max] given a current number

    }

}
