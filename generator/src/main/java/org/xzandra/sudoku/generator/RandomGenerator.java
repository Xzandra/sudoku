package org.xzandra.sudoku.generator;

import java.util.Random;

/**
 * Common logic for generating random int value.
 */
public final class RandomGenerator {
    public static final int MIN_INDEX = 0;
    private static Random random = new Random();

    private RandomGenerator() {
    }

    public static int getIndex(int upperBound) {
        return random.ints(MIN_INDEX, upperBound)
                     .limit(1)
                     .findFirst()
                     .getAsInt();
    }
}
