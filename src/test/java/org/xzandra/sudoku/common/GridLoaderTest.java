package org.xzandra.sudoku.common;

import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.model.SudokuGrid;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridLoaderTest {

    @Test
    void loadCsvWithSolvedGrid() throws URISyntaxException {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/loader/sudoku_table_full.csv")
                                         .toURI();
        final SudokuGrid grid = new GridLoader().loadGridFromCsv(sudokuPath);
        assertEquals(8, grid.getCell(0)
                            .getValue());
        assertEquals(0, grid.getCell(0)
                            .getAvailables()
                            .size());
    }

    @Test
    void loadCsvWithSolvalbleGrid2() throws URISyntaxException {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/loader/sudoku_table_solvable.csv")
                                         .toURI();
        final SudokuGrid grid = new GridLoader().loadGridFromCsv(sudokuPath);
        assertEquals(0, grid.getCell(0)
                            .getValue());
        assertEquals(2, grid.getCell(0)
                            .getAvailables()
                            .size());
    }
}