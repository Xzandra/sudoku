package org.xzandra.sudoku.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GridTest {
    @Test
    void gridInitialization() {
        Grid grid = new Grid();
        assertEquals(0, grid.getCellValue(1, 5));
    }

    @Test
    void isValidValueForRow() {
        Grid grid = new Grid();
        grid.setRandomCellValue(0, 0);
        final int cellValue = grid.getCellValue(0, 0);
        final int valueToCheck = cellValue + 1 <= 9 ? cellValue + 1 : cellValue - 1;
        assertTrue(grid.isValidValueForRow(0, valueToCheck));
    }

    @Test
    void isInvalidValueForRow() {
        Grid grid = new Grid();
        grid.setRandomCellValue(0, 0);
        final int cellValue = grid.getCellValue(0, 0);
        assertFalse(grid.isValidValueForRow(0, cellValue));
    }

    @Test
    void isValidValueForColumn() {
        Grid grid = new Grid();
        grid.setRandomCellValue(0, 0);
        final int cellValue = grid.getCellValue(0, 0);
        final int valueToCheck = cellValue + 1 <= 9 ? cellValue + 1 : cellValue - 1;
        assertTrue(grid.isValidValueForColumn(0, valueToCheck));
    }

    @Test
    void isInvalidValueForColumn() {
        Grid grid = new Grid();
        grid.setRandomCellValue(0, 0);
        final int cellValue = grid.getCellValue(0, 0);
        assertFalse(grid.isValidValueForColumn(0, cellValue));
    }

    @Test
    void isValidValueForBlock() {
        Grid grid = new Grid();
        grid.setRandomCellValue(0, 0);
        final int cellValue = grid.getCellValue(0, 0);
        final int valueToCheck = cellValue + 1 <= 9 ? cellValue + 1 : cellValue - 1;
        assertTrue(grid.isValidValueForBlock(0, 0, valueToCheck));
    }

    @Test
    void isInvalidValueForBlock() {
        Grid grid = new Grid();
        grid.setRandomCellValue(0, 0);
        final int cellValue = grid.getCellValue(0, 0);
        assertFalse(grid.isValidValueForBlock(0, 0, cellValue));
    }
}