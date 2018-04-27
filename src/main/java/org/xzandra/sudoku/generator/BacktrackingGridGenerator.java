package org.xzandra.sudoku.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xzandra.sudoku.common.ValidValuesUtils;
import org.xzandra.sudoku.model.SudokuCell;
import org.xzandra.sudoku.model.SudokuGrid;

import java.util.List;

import static org.xzandra.sudoku.common.GridConstants.TOTAL_GRID_SIZE;

/**
 * Generator of the sudoku grid: backtracking algorithm.
 */
public class BacktrackingGridGenerator {
    private static final Logger logger = LogManager.getLogger(BacktrackingGridGenerator.class);

    public SudokuGrid generate() throws Exception {
        final SudokuGrid grid = new SudokuGrid();

        logger.debug("Grid {} generation started", grid.getId());

        int cellIndex = 0;
        while (cellIndex < TOTAL_GRID_SIZE) {
            final boolean cellValueSet = setCellValue(cellIndex, grid);
            if (!cellValueSet) {
                grid.getCell(cellIndex--)
                    .reset();
                logger.debug("Going back to cell({})", cellIndex);
                if (cellIndex < 0) {
                    logger.error("Grid {} generation failed", grid.getId());
                    throw new Exception("Error generation grid " + grid.getId());
                }
                final int currentValue = grid.getCell(cellIndex)
                                             .getValue();
                grid.getCell(cellIndex)
                    .removeAvailaible(currentValue);
            } else {
                cellIndex++;
            }
        }
        logger.debug("Grid {} generation successfully", grid.getId());
        logger.debug(grid);
        return grid;
    }

    private boolean setCellValue(final int cellIndex, final SudokuGrid grid) {
        logger.debug("Setting value for cell({})", cellIndex);
        final SudokuCell cell = grid.getCell(cellIndex);
        final List<Integer> cellValidValues = grid.getCell(cellIndex)
                                                  .getAvailables();
        do {
            final Integer value = ValidValuesUtils.getRandomAvailableValue(cellValidValues);
            if (cellValidValues
                    .contains(value) && ValidValuesUtils.isValidValue(cellIndex, value, grid)) {
                cell.setValue(value);
                grid.getCell(cellIndex)
                    .removeAvailaible(value);
                logger.debug("cell({}) set value {}", cellIndex, value);
                return true;
            } else {
                grid.getCell(cellIndex)
                    .removeAvailaible(value);
                logger.debug("cell({}) discard value {}", cellIndex, value);
            }
        } while (!cellValidValues.isEmpty());

        logger.debug("cell({}) no available values left", cellIndex);
        return false;
    }
}
