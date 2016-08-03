package utils;

public class MathUtils {

    // Clamp Method from libGDX: https://github.com/libgdx/libgdx/blob/master/gdx/src/com/badlogic/gdx/math/MathUtils.java
    static public int clamp(int value, int min, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    static public boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }
}
