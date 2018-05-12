package org.xzandra.sudoku.solver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xzandra.sudoku.common.SudokuGridUtils;
import org.xzandra.sudoku.common.ValidValuesUtils;
import org.xzandra.sudoku.model.old.SudokuCellImpl;
import org.xzandra.sudoku.model.old.SudokuGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * Solver of the sudoku grid: backtracking algorithm with a brute force approach.
 * Very inefficient with hard puzzles - run too long.
 */
public class BacktrackingGridSolver {
    private static final Logger logger = LogManager.getLogger(BacktrackingGridSolver.class);
    private List<Integer> track = new ArrayList<>();

    public boolean isValidSudokuGrid(final SudokuGrid grid) {

        return false;
    }

    public void solve(final SudokuGrid grid) throws Exception {
        logger.debug("Starting searching solution for grid {}", grid.getId());
        final SudokuCellImpl cellWithMinAvailables = SudokuGridUtils.findCellWithMinAvailables(grid);
        if (cellWithMinAvailables == null) {
            throw new Exception("Sudoku grid is not solvable or already solved");
        }

        SudokuCellImpl nextCellToTry = solveInternal(cellWithMinAvailables, grid);
        while (nextCellToTry != null) {
            nextCellToTry = solveInternal(nextCellToTry, grid);
        }

        logger.debug("SudokuBoard " + grid.getId() + " is solved? " + grid.isSolved());
        logger.debug(grid);
    }

    private SudokuCellImpl solveInternal(final SudokuCellImpl currentCell, final SudokuGrid grid) throws Exception {
        logger.debug("Selected cell {}", currentCell);
        if (currentCell != null) {
            final boolean valueIsSet = tryToSetCellValue(currentCell, grid);
            if (valueIsSet) {
                if (grid.isSolved()) {
                    logger.debug("SudokuBoard {} is solved", grid.getId());
                    return null;
                } else {
                    track.add(currentCell.getIndex());
                    logger.debug("Searching next cell");
                    return SudokuGridUtils.findCellWithMinAvailables(grid);
                }
            } else {
                if (track.size() >= 1) {
                    logger.debug("Trying backtracking from {}", track);
                    currentCell.reset();
                    final Integer previousIndex = track.get(track.size() - 1);
                    track.remove(previousIndex);
                    final SudokuCellImpl previousCell = grid.getCell(previousIndex);
                    previousCell.setValue(0);
                    return previousCell;
                } else {
                    logger.debug("SudokuBoard {} is not solved, no backtracking available", grid.getId());
                    return null;
                }
            }
        }
        logger.debug("SudokuBoard {} is not solved, no available cell", grid.getId());
        return null;
    }

    private boolean tryToSetCellValue(final SudokuCellImpl cell, final SudokuGrid grid) {
        new ArrayList<>(cell.getPossibilities())
                .stream()
                .forEach(value -> {
                    if (!ValidValuesUtils.isValidValue(cell.getIndex(), value, grid)) {
                        cell.removePossibility(value);
                    }
                });
        if (cell.getPossibilities()
                .size() > 0) {
            cell.setValue(cell.getPossibilities()
                              .get(0));
            logger.debug("SudokuCell {} is set to", cell.getIndex(), cell.getValue());
        }

        return cell.getValue() != 0;
    }
}
