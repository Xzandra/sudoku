package org.xzandra.sudoku.solver;

import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.model.Grid;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.xzandra.sudoku.common.GridConstants.TOTAL_CELL_SIZE;

class GridLoaderTest {
    @Test
    void loadCsvWithFullGrid() throws URISyntaxException {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/sudoku_table_full.csv")
                                         .toURI();
        final Grid grid = new GridLoader().loadCsv(sudokuPath);
        assertAll(
                () -> assertEquals(8, grid.getCell(0)
                                          .getValue()),
                () -> assertEquals(0, grid.getCellValidValues(0)
                                          .size()),
                () -> assertEquals(TOTAL_CELL_SIZE, grid.getCells().length),
                () -> assertEquals(TOTAL_CELL_SIZE, grid.getValidValues().length));
    }

    @Test
    void loadCsvWithSolvalbleGrid() throws URISyntaxException {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/sudoku_table_solvable.csv")
                                         .toURI();
        final Grid grid = new GridLoader().loadCsv(sudokuPath);
        assertAll(
                () -> assertEquals(0, grid.getCell(0)
                                          .getValue()),
                () -> assertEquals(2, grid.getCellValidValues(0)
                                          .size()),
                () -> assertEquals(2, grid.getCell(2)
                                          .getValue()),
                () -> assertEquals(2, grid.getCellValidValues(0)
                                          .size()),
                () -> assertEquals(TOTAL_CELL_SIZE, grid.getCells().length),
                () -> assertEquals(TOTAL_CELL_SIZE, grid.getValidValues().length));
    }
}