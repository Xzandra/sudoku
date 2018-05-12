package org.xzandra.sudoku.solver;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.common.GridLoader;
import org.xzandra.sudoku.model.old.SudokuGrid;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BacktrackingGridSolverTest {
    private static SudokuGrid gridSolutionVeryHard;
    private static SudokuGrid gridSolutionHard;
    private final BacktrackingGridSolver backtrackingGridSolver = new BacktrackingGridSolver();

    @BeforeAll
    public static void setUp() throws URISyntaxException {
        gridSolutionVeryHard = new GridLoader().loadGrid(BacktrackingGridSolverTest.class.getClassLoader()
                                                                                         .getResource("sudokutables/model/sudoku_very_hard_solution.csv")
                                                                                         .toURI());
        gridSolutionHard = new GridLoader().loadGrid(BacktrackingGridSolverTest.class.getClassLoader()
                                                                                     .getResource("sudokutables/model/sudoku_hard_solution.csv")
                                                                                     .toURI());
    }

    @Test
    void solveEasy() throws Exception {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/model/sudoku_easy.csv")
                                         .toURI();
        final SudokuGrid grid = new GridLoader().loadGrid(sudokuPath);
        backtrackingGridSolver.solve(grid);
        assertTrue(grid.isSolved());
        assertEquals(gridSolutionVeryHard, grid);
    }

    @Test
    void solveVeryHard1() throws Exception {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/model/sudoku_very_hard1.csv")
                                         .toURI();
        final SudokuGrid grid = new GridLoader().loadGrid(sudokuPath);
        backtrackingGridSolver.solve(grid);
        assertTrue(grid.isSolved());
        assertEquals(gridSolutionVeryHard, grid);
    }

    @Test
    void solveVeryHard2() throws Exception {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/model/sudoku_very_hard2.csv")
                                         .toURI();
        final SudokuGrid grid = new GridLoader().loadGrid(sudokuPath);
        backtrackingGridSolver.solve(grid);
        assertTrue(grid.isSolved());
        assertEquals(gridSolutionVeryHard, grid);
    }

    @Test
    void solveHard() throws Exception {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/model/sudoku_hard.csv")
                                         .toURI();
        final SudokuGrid grid = new GridLoader().loadGrid(sudokuPath);
        backtrackingGridSolver.solve(grid);
        assertTrue(grid.isSolved());
        assertEquals(gridSolutionHard, grid);
    }
}