package org.xzandra.sudoku.common;

import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.model.SudokuGrid;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.xzandra.sudoku.common.GridConstants.GRID_RANGE_SIZE;

class ValidValuesUtilsTest {
    @Test
    void getDefaultValidValues() {
        final ArrayList<Integer> defaultValidValues = ValidValuesUtils.getDefaultAvailables();
        Range<Integer> sudokuValueRange = Range.between(1, GRID_RANGE_SIZE + 1);
        final boolean allMatch = IntStream.range(0, 9)
                                          .allMatch(index -> sudokuValueRange.contains(defaultValidValues.get(index)));
        assertTrue(allMatch);
    }

    @Test
    void isValidForRow() {
        SudokuGrid grid = new SudokuGrid();
        grid.getCell(0)
            .setValue(3);

        assertTrue(ValidValuesUtils.isValidForRow(grid.getCell(0)
                                                      .getRow(), 4, grid));
        assertTrue(ValidValuesUtils.isValidForRow(grid.getCell(0)
                                                      .getRow(), 1, grid));
        assertTrue(ValidValuesUtils.isValidForRow(grid.getCell(0)
                                                      .getRow(), 9, grid));
    }

    @Test
    void isInvalidForRow() {
        SudokuGrid grid = new SudokuGrid();
        grid.getCell(0)
            .setValue(3);
        assertFalse(ValidValuesUtils.isValidForRow(grid.getCell(0)
                                                       .getRow(), 3, grid));
    }

    @Test
    void isValidForColumn() {
        SudokuGrid grid = new SudokuGrid();
        grid.getCell(0)
            .setValue(3);
        assertTrue(ValidValuesUtils.isValidForColumn(grid.getCell(0)
                                                         .getColumn(), 4, grid));
        assertTrue(ValidValuesUtils.isValidForColumn(grid.getCell(0)
                                                         .getColumn(), 1, grid));
        assertTrue(ValidValuesUtils.isValidForColumn(grid.getCell(0)
                                                         .getColumn(), 9, grid));
    }

    @Test
    void isInvalidForColumn() {
        SudokuGrid grid = new SudokuGrid();
        grid.getCell(0)
            .setValue(3);
        assertFalse(ValidValuesUtils.isValidForColumn(grid.getCell(0)
                                                          .getColumn(), 3, grid));
    }

    @Test
    void isValidForSquare() {
        SudokuGrid grid = new SudokuGrid();
        grid.getCell(0)
            .setValue(3);
        assertTrue(ValidValuesUtils.isValidForSquare(grid.getCell(0)
                                                         .getSquare(), 4, grid));
        assertTrue(ValidValuesUtils.isValidForSquare(grid.getCell(0)
                                                         .getSquare(), 1, grid));
        assertTrue(ValidValuesUtils.isValidForSquare(grid.getCell(0)
                                                         .getSquare(), 9, grid));
    }

    @Test
    void isInvalidForSquare() {
        SudokuGrid grid = new SudokuGrid();
        grid.getCell(0)
            .setValue(3);
        assertFalse(ValidValuesUtils.isValidForSquare(grid.getCell(0)
                                                          .getSquare(), 3, grid));
    }
}