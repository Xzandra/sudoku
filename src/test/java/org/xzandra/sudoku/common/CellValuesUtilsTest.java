package org.xzandra.sudoku.common;

import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.model.Cell;
import org.xzandra.sudoku.model.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.xzandra.sudoku.common.GridConstants.GRID_SIZE;
import static org.xzandra.sudoku.common.GridConstants.TOTAL_CELL_SIZE;

class CellValuesUtilsTest {
    private final CellValuesUtils cellValuesUtils = new CellValuesUtils();

    @Test
    void isValidForRow() {
        Grid grid = new Grid();
        grid.setCellValue(0, 3);

        assertTrue(cellValuesUtils.isValidForRow(grid.getCell(0)
                                                     .getRow(), 4, grid.getCells()));
    }

    @Test
    void isInvalidForRow() {
        Grid grid = new Grid();
        grid.setCellValue(0, 3);
        assertFalse(cellValuesUtils.isValidForRow(grid.getCell(0)
                                                      .getRow(), 3, grid.getCells()));
    }

    @Test
    void isInvalidForRowOutsideOfRange() {
        Grid grid = new Grid();
        grid.setCellValue(0, 3);
        assertFalse(cellValuesUtils.isValidForRow(grid.getCell(0)
                                                      .getRow(), TOTAL_CELL_SIZE, grid.getCells()));
    }

    @Test
    void isValidForColumn() {
        Grid grid = new Grid();
        grid.setCellValue(0, 3);
        assertTrue(cellValuesUtils.isValidForColumn(grid.getCell(0)
                                                        .getColumn(), 4, grid.getCells()));
    }

    @Test
    void isInvalidForColumn() {
        Grid grid = new Grid();
        grid.setCellValue(0, 3);
        assertFalse(cellValuesUtils.isValidForColumn(grid.getCell(0)
                                                         .getColumn(), 3, grid.getCells()));
    }

    @Test
    void isInvalidForColumnOutsideOfRange() {
        Grid grid = new Grid();
        grid.setCellValue(0, 3);
        assertFalse(cellValuesUtils.isValidForColumn(grid.getCell(0)
                                                         .getColumn(), TOTAL_CELL_SIZE, grid.getCells()));
    }

    @Test
    void isValidForSquare() {
        Grid grid = new Grid();
        grid.setCellValue(0, 3);
        assertTrue(cellValuesUtils.isValidForSquare(grid.getCell(0)
                                                        .getSquare(), 4, grid.getCells()));
    }

    @Test
    void isInvalidForSquare() {
        Grid grid = new Grid();
        grid.setCellValue(0, 3);
        assertFalse(cellValuesUtils.isValidForSquare(grid.getCell(0)
                                                         .getSquare(), 3, grid.getCells()));
    }

    @Test
    void isInvalidForSquareOutsideOfRange() {
        Grid grid = new Grid();
        grid.setCellValue(0, 3);
        assertFalse(cellValuesUtils.isValidForSquare(grid.getCell(0)
                                                         .getColumn(), TOTAL_CELL_SIZE, grid.getCells()));
    }

    @Test
    void getDefaultValidValues() {
        final ArrayList<Integer> defaultValidValues = cellValuesUtils.getDefaultValidValues();
        Range<Integer> sudokuValueRange = Range.between(1, GRID_SIZE + 1);
        final boolean allMatch = IntStream.range(0, 9)
                                          .allMatch(index -> sudokuValueRange.contains(defaultValidValues.get(index)));
        assertTrue(allMatch);
    }

    @Test
    void initializeValidValuesForCells() {
        Cell[] cells = new Grid().getCells();
        final int cellIndex = 1;
        final int cellValue = 2;
        cells[cellIndex].setValue(cellValue);
        cells[cellIndex].setRemovable(false);

        final List<Integer>[] valuesForCells = cellValuesUtils.initializeValidValuesForCells(cells);

        assertAll(() -> assertFalse(valuesForCells[0].contains(cellValue)),
                () -> assertTrue(valuesForCells[1].isEmpty()),
                () -> assertFalse(valuesForCells[2].contains(cellValue)));
    }
}