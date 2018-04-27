package org.xzandra.sudoku.solver;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.common.GridLoader;
import org.xzandra.sudoku.model.SudokuGrid;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BacktrackingGridSolverTest {
    private static SudokuGrid gridSolution;
    private final BacktrackingGridSolver backtrackingGridSolver = new BacktrackingGridSolver();

    @BeforeAll
    public static void setUp() throws URISyntaxException {
        final URI sudokuPath = BacktrackingGridSolverTest.class.getClassLoader()
                                                               .getResource("sudokutables/solver/sudoku_solution.csv")
                                                               .toURI();
        gridSolution = new GridLoader().loadGridFromCsv(sudokuPath);
    }

    @Test
    void solveEasy() throws Exception {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/solver/sudoku_easy.csv")
                                         .toURI();
        final SudokuGrid grid = new GridLoader().loadGridFromCsv(sudokuPath);
        backtrackingGridSolver.solve(grid);
        assertTrue(grid.isSolved());
        assertEquals(gridSolution, grid);
    }

    @Test
    void solveVeryHard1() throws Exception {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/solver/sudoku_very_hard1.csv")
                                         .toURI();
        final SudokuGrid grid = new GridLoader().loadGridFromCsv(sudokuPath);
        backtrackingGridSolver.solve(grid);
        assertTrue(grid.isSolved());
        assertEquals(gridSolution, grid);
    }

    @Test
    void solveVeryHard2() throws Exception {
        final URI sudokuPath = getClass().getClassLoader()
                                         .getResource("sudokutables/solver/sudoku_very_hard2.csv")
                                         .toURI();
        final SudokuGrid grid = new GridLoader().loadGridFromCsv(sudokuPath);
        backtrackingGridSolver.solve(grid);
        assertTrue(grid.isSolved());
        assertEquals(gridSolution, grid);
    }
}