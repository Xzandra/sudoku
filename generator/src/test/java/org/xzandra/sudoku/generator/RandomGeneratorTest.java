package org.xzandra.sudoku.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.xzandra.sudoku.generator.RandomGenerator.MIN_INDEX;

class RandomGeneratorTest {
    @Test
    void getRandomValue() {
        final int randomValue = RandomGenerator.getIndex(10);
        assertAll(
                () -> assertTrue(randomValue >= MIN_INDEX),
                () -> assertTrue(randomValue < 10));
    }
}