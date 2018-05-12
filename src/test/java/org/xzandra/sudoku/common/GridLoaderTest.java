package org.xzandra.sudoku.common;

import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.model.SudokuBoard;
import org.xzandra.sudoku.model.old.SudokuGrid;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.xzandra.sudoku.model.SudokuBoard.BOARD_SIZE;

class GridLoaderTest {
    @Test
    void loadCsvWithSolvedGridIntoSudokuGrid() throws URISyntaxException {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/loader/sudoku_table_full.csv")
                                         .toURI();
        final SudokuGrid grid = new GridLoader().loadGrid(sudokuPath);
        assertEquals(8, grid.getCell(0)
                            .getValue());
        assertEquals(0, grid.getCell(0)
                            .getPossibilities()
                            .size());
    }

    @Test
    void loadCsvWithSolvableGridIntoSudokuGrid() throws URISyntaxException {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/loader/sudoku_table_solvable.csv")
                                         .toURI();
        final SudokuGrid grid = new GridLoader().loadGrid(sudokuPath);
        assertEquals(0, grid.getCell(0)
                            .getValue());
        assertEquals(2, grid.getCell(0)
                            .getPossibilities()
                            .size());
    }

    @Test
    void loadCsvWithSolvedGridIntoSudokuBoard() throws URISyntaxException {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/loader/sudoku_table_full.csv")
                                         .toURI();
        final SudokuBoard sudokuBoard = new GridLoader().loadBoard(sudokuPath);
        assertEquals(8, sudokuBoard.getCell(0, 0)
                                   .getValue());
        assertEquals(0, sudokuBoard.getOptions(0, 0)
                                   .size());
    }

    @Test
    void loadCsvWithSolvableGridIntoSudokuBoard() throws URISyntaxException {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/loader/sudoku_table_solvable.csv")
                                         .toURI();
        final SudokuBoard sudokuBoard = new GridLoader().loadBoard(sudokuPath);
        assertEquals(0, sudokuBoard.getCell(0, 0)
                                   .getValue());
        assertEquals(2, sudokuBoard.getOptions(0, 0)
                                   .size());
    }

    @Test
    void loadCsvWithSolvedGridIntoArray() throws URISyntaxException {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/loader/sudoku_table_full.csv")
                                         .toURI();
        final int[] cells = new GridLoader().loadCellsFromCsv(sudokuPath);
        assertEquals(BOARD_SIZE * BOARD_SIZE, cells.length);
        assertEquals(8, cells[0]);
        assertEquals(7, cells[8]);
        assertEquals(9, cells[17]);
    }

    @Test
    void loadCsvWithSolvableGridIntoArray() throws URISyntaxException {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/loader/sudoku_table_solvable.csv")
                                         .toURI();
        final int[] cells = new GridLoader().loadCellsFromCsv(sudokuPath);
        assertEquals(BOARD_SIZE * BOARD_SIZE, cells.length);
        assertEquals(0, cells[0]);
        assertEquals(0, cells[8]);
        assertEquals(7, cells[13]);
        assertEquals(3, cells[17]);
    }
}