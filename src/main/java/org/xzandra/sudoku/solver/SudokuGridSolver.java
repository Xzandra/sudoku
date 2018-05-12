package org.xzandra.sudoku.solver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xzandra.sudoku.model.SudokuBoard;
import org.xzandra.sudoku.model.SudokuCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Sudoku model with different approaches:
 * 1. Apply 'single possibility rule' - find cells where only one value is possible
 * 2. Apply 'two out of three rule' - find cells with only one possible solution within the groups of three rows and columns (squares)
 * 3. Apply 'only square rule' - identify area(row, column, square) with cell that because of restrictions has only one
 * possible value
 */
public class SudokuGridSolver {
    private static final Logger logger = LogManager.getLogger(SudokuGridSolver.class);

    public static boolean solve(final SudokuBoard board) {
        applySinglePossibilityRule(board);
        applyTwoOutOfThreeRule(board);
        applyOnlySquareRule(board);
        if (board.isFilled()) {
            return true;
        }

        return false;
    }

    public static void applySinglePossibilityRule(final SudokuBoard board) {
        List<SudokuCell> cellsWithOneSolution = SudokuGridSolverUtils.findCellsWithSingleOption(board);
        while (!cellsWithOneSolution.isEmpty()) {
            cellsWithOneSolution.stream()
                                .forEach(sudokuCell -> sudokuCell.setValue(board.getOptions(sudokuCell)
                                                                                .get(0)));
            cellsWithOneSolution = SudokuGridSolverUtils.findCellsWithSingleOption(board);
        }
    }

    public static void applyTwoOutOfThreeRule(final SudokuBoard board) {
        SudokuCell cellsWithTwoOutOfThreeSolution = SudokuGridSolverUtils.findCellWithTwoOutOfThreeOption(board);
        while (cellsWithTwoOutOfThreeSolution != null) {
            cellsWithTwoOutOfThreeSolution.setValue(cellsWithTwoOutOfThreeSolution.getOption());
            cellsWithTwoOutOfThreeSolution = SudokuGridSolverUtils.findCellWithTwoOutOfThreeOption(board);
        }
    }

    public static void applyOnlySquareRule(final SudokuBoard board) {
        SudokuCell cellWithOnlySquareOption = SudokuGridSolverUtils.findCellWithOnlySquareOption(board);
        while (cellWithOnlySquareOption != null) {
            cellWithOnlySquareOption.setValue(cellWithOnlySquareOption.getOption());
            cellWithOnlySquareOption = SudokuGridSolverUtils.findCellWithOnlySquareOption(board);
        }
    }

    private static void applyBacktracking(final SudokuBoard board) {
        final List<Integer> track = new ArrayList<>();
        final SudokuCell cellWithMinOptions = SudokuGridSolverUtils.findCellWithMinOptions(board);
        while (cellWithMinOptions != null) {
            final Integer optionToTry = board.getOptions(cellWithMinOptions)
                                             .get(0);
            cellWithMinOptions.setValue(optionToTry);
            cellWithMinOptions.getCheckedOptions()
                              .add(optionToTry);
            logger.debug("SudokuCell {} is set to", cellWithMinOptions, cellWithMinOptions.getValue());
        }
    }
}
