package org.xzandra.sudoku.model;

import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.generator.Grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GridTest {
    @Test
    void gridInitialization() {
        Grid grid = new Grid();
        assertEquals(0, grid.getCell(1)
                            .getValue());
    }

    @Test
    void setRandomCellValue() {
        Grid grid = new Grid();
        grid.setRandomCellValue(0);
        assertNotEquals(0, grid.getCell(0)
                               .getValue());
    }

    @Test
    void isValidValueForRow() {
        Grid grid = new Grid();
        grid.setRandomCellValue(0);
        final int cellValue = grid.getCell(0)
                                  .getValue();
        final int valueToCheck = cellValue + 1 <= 9 ? cellValue + 1 : cellValue - 1;
        assertTrue(grid.isValidValueForRow(0, valueToCheck));
    }

    @Test
    void isInvalidValueForRow() {
        Grid grid = new Grid();
        grid.setRandomCellValue(0);
        final int cellValue = grid.getCell(0)
                                  .getValue();
        assertFalse(grid.isValidValueForRow(0, cellValue));
    }

    @Test
    void isValidValueForColumn() {
        Grid grid = new Grid();
        grid.setRandomCellValue(0);
        final int cellValue = grid.getCell(0)
                                  .getValue();
        final int valueToCheck = cellValue + 1 <= 9 ? cellValue + 1 : cellValue - 1;
        assertTrue(grid.isValidValueForColumn(0, valueToCheck));
    }

    @Test
    void isInvalidValueForColumn() {
        Grid grid = new Grid();
        grid.setRandomCellValue(0);
        final int cellValue = grid.getCell(0)
                                  .getValue();
        assertFalse(grid.isValidValueForColumn(0, cellValue));
    }

    @Test
    void isValidValueForSquare() {
        Grid grid = new Grid();
        grid.setRandomCellValue(0);
        final int cellValue = grid.getCell(0)
                                  .getValue();
        final int valueToCheck = cellValue + 1 <= 9 ? cellValue + 1 : cellValue - 1;
        assertTrue(grid.isValidValueForSquare(0, valueToCheck));
    }

    @Test
    void isInvalidValueForSquare() {
        Grid grid = new Grid();
        grid.setRandomCellValue(0);
        final int cellValue = grid.getCell(0)
                                  .getValue();
        assertFalse(grid.isValidValueForSquare(0, cellValue));
    }
}