package org.xzandra.sudoku.solver;

import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.common.GridLoader;
import org.xzandra.sudoku.model.SudokuBoard;
import org.xzandra.sudoku.model.SudokuCell;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SudokuGridSolverUtilsTest {
    @Test
    void findCellsWithSingleOption() throws URISyntaxException {
        final URI sudokuPath = SudokuGridSolverUtilsTest.class.getClassLoader()
                                                              .getResource("sudokutables/solver/sudoku_single_option.csv")
                                                              .toURI();
        final int[] cells = new GridLoader().loadCellsFromCsv(sudokuPath);
        final SudokuBoard sudokuBoard = new SudokuBoard(cells);
        final List<SudokuCell> cellsWithSingleOption = SudokuGridSolverUtils.findCellsWithSingleOption(sudokuBoard);
        assertEquals(10, cellsWithSingleOption.size());
        assertEquals(1, cellsWithSingleOption.get(0)
                                             .getRow());
        assertEquals(0, cellsWithSingleOption.get(0)
                                             .getColumn());
        assertEquals(9, cellsWithSingleOption.get(0)
                                             .getOption());
    }

    @Test
    void findCellWithTwoOutOfThreeOption() throws URISyntaxException {
        final URI sudokuPath = SudokuGridSolverUtilsTest.class.getClassLoader()
                                                              .getResource("sudokutables/solver/sudoku_two_out_of_three.csv")
                                                              .toURI();
        final int[] cells = new GridLoader().loadCellsFromCsv(sudokuPath);
        final SudokuBoard sudokuBoard = new SudokuBoard(cells);
        final SudokuCell cellWithTwoOutOfThreeOption = SudokuGridSolverUtils.findCellWithTwoOutOfThreeOption(sudokuBoard);
        assertEquals(1, cellWithTwoOutOfThreeOption.getRow());
        assertEquals(8, cellWithTwoOutOfThreeOption.getColumn());
        assertEquals(1, cellWithTwoOutOfThreeOption.getOption());
    }

    @Test
    void findCellWithOnlySquareOption() throws URISyntaxException {
        final URI sudokuPath = SudokuGridSolverUtilsTest.class.getClassLoader()
                                                              .getResource("sudokutables/solver/sudoku_only_square.csv")
                                                              .toURI();
        final int[] cells = new GridLoader().loadCellsFromCsv(sudokuPath);
        final SudokuBoard sudokuBoard = new SudokuBoard(cells);
        final SudokuCell cellWithOnlySquareOption = SudokuGridSolverUtils.findCellWithOnlySquareOption(sudokuBoard);
        assertEquals(0, cellWithOnlySquareOption.getRow());
        assertEquals(2, cellWithOnlySquareOption.getColumn());
        assertEquals(3, cellWithOnlySquareOption.getOption());
    }

    @Test
    void findCellWithMinOptions() throws URISyntaxException {
        final URI sudokuPath = SudokuGridSolverUtilsTest.class.getClassLoader()
                                                              .getResource("sudokutables/solver/sudoku_min_options.csv")
                                                              .toURI();
        final int[] cells = new GridLoader().loadCellsFromCsv(sudokuPath);
        final SudokuBoard sudokuBoard = new SudokuBoard(cells);
        final SudokuCell cellWithMinOptions = SudokuGridSolverUtils.findCellWithMinOptions(sudokuBoard);
        assertEquals(0, cellWithMinOptions.getRow());
        assertEquals(4, cellWithMinOptions.getColumn());
        final List<Integer> options = sudokuBoard.getOptions(cellWithMinOptions);
        assertEquals(1, options.size());
        assertEquals(3, options.get(0)
                               .intValue());
    }
}