package org.xzandra.sudoku.solver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xzandra.sudoku.common.SudokuGridUtils;
import org.xzandra.sudoku.common.ValidValuesUtils;
import org.xzandra.sudoku.model.SudokuCell;
import org.xzandra.sudoku.model.SudokuGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * Solver of the sudoku grid: backtracking algorithm.
 */
public class BacktrackingGridSolver {
    private static final Logger logger = LogManager.getLogger(BacktrackingGridSolver.class);
    private List<Integer> track = new ArrayList<>();

    public boolean isValidSudokuGrid(final SudokuGrid grid) {

        return false;
    }

    public void solve(final SudokuGrid grid) throws Exception {
        logger.debug("Starting searching solution for grid {}", grid.getId());
        final SudokuCell cellWithMinAvailables = SudokuGridUtils.findCellWithMinAvailables(grid);
        if (cellWithMinAvailables == null) {
            throw new Exception("Sudoku grid is not solvable or already solved");
        }

        SudokuCell nextCellToTry = solveInternal(cellWithMinAvailables, grid);
        while (nextCellToTry != null) {
            nextCellToTry = solveInternal(nextCellToTry, grid);
        }

        logger.debug("Grid " + grid.getId() + " is solved? " + grid.isSolved());
        logger.debug(grid);
    }

    private SudokuCell solveInternal(final SudokuCell currentCell, final SudokuGrid grid) throws Exception {
        logger.debug("Selected cell {}", currentCell);
        if (currentCell != null) {
            final boolean valueIsSet = tryToSetCellValue(currentCell, grid);
            if (valueIsSet) {
                if (grid.isSolved()) {
                    logger.debug("Grid {} is solved", grid.getId());
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
                    final SudokuCell previousCell = grid.getCell(previousIndex);
                    previousCell.setValue(0);
                    return previousCell;
                } else {
                    logger.debug("Grid {} is not solved, no backtracking available", grid.getId());
                    return null;
                }
            }
        }
        logger.debug("Grid {} is not solved, no available cell", grid.getId());
        return null;
    }

    private boolean tryToSetCellValue(final SudokuCell cell, final SudokuGrid grid) {
        new ArrayList<>(cell.getAvailables())
                .stream()
                .forEach(value -> {
                    if (!ValidValuesUtils.isValidValue(cell.getIndex(), value, grid)) {
                        cell.removeAvailaible(value);
                    }
                });
        if (cell.getAvailables()
                .size() > 0) {
            cell.setValue(cell.getAvailables()
                              .get(0));
            logger.debug("Cell {} is set to", cell.getIndex(), cell.getValue());
        }

        return cell.getValue() != 0;
    }
}
