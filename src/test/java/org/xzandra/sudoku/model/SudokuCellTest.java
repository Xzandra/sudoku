package org.xzandra.sudoku.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SudokuCellTest {
    private final SudokuCell cell = new SudokuCell(12, 1);

    @Test
    public void cellsEqual() {
        assertEquals(cell, new SudokuCell(12, 1));
        assertNotEquals(cell, new SudokuCell(11, 1));
    }

    @Test
    public void cellsHashCode() {
        assertEquals(cell.hashCode(), new SudokuCell(12, 1).hashCode());
        assertNotEquals(cell.hashCode(), new SudokuCell(11, 1).hashCode());
    }
}