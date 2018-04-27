package org.xzandra.sudoku.common;

import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.model.SudokuCell;
import org.xzandra.sudoku.model.SudokuGrid;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 *
 */
class SudokuGridUtilsTest {
    @Test
    void findCellWithMinAvailablesWhenGridIsSolved() throws URISyntaxException {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/loader/sudoku_table_full.csv")
                                         .toURI();
        final SudokuGrid grid = new GridLoader().loadGridFromCsv(sudokuPath);
        final SudokuCell cellWithMinAvailables = SudokuGridUtils.findCellWithMinAvailables(grid);
        assertNull(cellWithMinAvailables);
    }

    @Test
    void findCellWithMinAvailablesWhenGridIsNotSolved() throws URISyntaxException {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/loader/sudoku_table_solvable.csv")
                                         .toURI();
        final SudokuGrid grid = new GridLoader().loadGridFromCsv(sudokuPath);
        final SudokuCell cellWithMinAvailables = SudokuGridUtils.findCellWithMinAvailables(grid);
        assertEquals(5, cellWithMinAvailables.getIndex());
    }
}