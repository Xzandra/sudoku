package org.xzandra.sudoku.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.common.GridLoader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SudokuBoardTest {
    private static int[] cells;

    @BeforeAll
    public static void setUp() throws URISyntaxException {
        final URI sudokuPath = SudokuBoardTest.class.getClassLoader()
                                                    .getResource("sudokutables/loader/sudoku_table_solvable.csv")
                                                    .toURI();
        cells = new GridLoader().loadCellsFromCsv(sudokuPath);
    }

    @Test
    public void boardInitialization() {
        final SudokuBoard sudokuBoard = new SudokuBoard(cells);

        assertEquals(0, sudokuBoard.getCell(0, 0)
                                   .getValue());
        assertEquals(4, sudokuBoard.getRow(1)[0].getValue());
        assertEquals(3, sudokuBoard.getColumn(1)[2].getValue());
        assertEquals(1, sudokuBoard.getSquare(2)[4].getValue());
    }

    @Test
    public void cellOptionsByIndices() {
        final SudokuBoard sudokuBoard = new SudokuBoard(cells);

        final List<Integer> options = sudokuBoard.getOptions(2, 0);
        assertEquals(2, options.size());
        assertTrue(options.contains(1));
        assertTrue(options.contains(5));
    }

    @Test
    public void cellOptionsByCell() {
        final SudokuBoard sudokuBoard = new SudokuBoard(cells);

        final List<Integer> options = sudokuBoard.getOptions(sudokuBoard.getCell(2, 0));
        assertEquals(2, options.size());
        assertTrue(options.contains(1));
        assertTrue(options.contains(5));
    }

    @Test
    public void isFilled() throws URISyntaxException {
        final URI sudokuPath = SudokuBoardTest.class.getClassLoader()
                                                    .getResource("sudokutables/loader/sudoku_table_full.csv")
                                                    .toURI();
        final int[] filledCells = new GridLoader().loadCellsFromCsv(sudokuPath);

        final SudokuBoard sudokuBoardSolvable = new SudokuBoard(cells);
        final SudokuBoard sudokuBoardFilled = new SudokuBoard(filledCells);

        assertTrue(sudokuBoardFilled.isFilled());
        assertFalse(sudokuBoardSolvable.isFilled());
    }

    @Test
    public void unfilledCellsStream() {
        final SudokuBoard sudokuBoard = new SudokuBoard(cells);
        final Stream<SudokuCell> unfilledCells = sudokuBoard.getUnfilledCellsStream();
        assertEquals(41, unfilledCells.count());
    }
}