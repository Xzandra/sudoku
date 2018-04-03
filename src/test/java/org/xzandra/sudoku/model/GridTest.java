package org.xzandra.sudoku.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.xzandra.sudoku.common.GridConstants.TOTAL_CELL_SIZE;

class GridTest {
    @Test
    void gridDefaultInitialization() {
        Grid grid = new Grid();

        assertAll(
                () -> assertEquals(0, grid.getCell(1)
                                          .getValue()),
                () -> assertEquals(9, grid.getCellValidValues(1)
                                          .size()),
                () -> assertEquals(TOTAL_CELL_SIZE, grid.getCells().length),
                () -> assertEquals(TOTAL_CELL_SIZE, grid.getValidValues().length));
    }

    @Test
    void gridCustomInitialization() {
        Grid defaultGrid = new Grid();
        Grid grid = new Grid(defaultGrid.getCells(), defaultGrid.getValidValues());
        assertAll(
                () -> assertEquals(defaultGrid.getCells(), grid.getCells()),
                () -> assertEquals(defaultGrid.getValidValues(), grid.getValidValues()));
    }
}