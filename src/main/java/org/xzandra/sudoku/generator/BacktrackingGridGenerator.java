package org.xzandra.sudoku.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xzandra.sudoku.common.CellValuesUtils;
import org.xzandra.sudoku.model.Cell;
import org.xzandra.sudoku.model.Grid;

import java.util.List;

import static org.xzandra.sudoku.common.GridConstants.TOTAL_CELL_SIZE;

/**
 * Generator of the sudoku grid: backtracking algorithm.
 */
public class BacktrackingGridGenerator {
    private static final Logger logger = LogManager.getLogger(BacktrackingGridGenerator.class);
    private CellValuesUtils cellValuesUtils = new CellValuesUtils();

    public Grid generate() throws Exception {
        final Grid grid = new Grid();

        logger.info("Grid {} generation started", grid.getId());

        int cellIndex = 0;
        while (cellIndex < TOTAL_CELL_SIZE) {
            final boolean cellValueSet = setCellValue(cellIndex, grid);
            if (!cellValueSet) {
                grid.resetCell(cellIndex--);
                logger.debug("Going back to cell({})", cellIndex);
                final int currentValue = grid.getCell(cellIndex)
                                             .getValue();
                grid.removeFromAvailableForCell(cellIndex, currentValue);
                if (cellIndex < 0) {
                    logger.error("Grid {} generation failed", grid.getId());
                    throw new Exception("Error generation grid " + grid.getId());
                }
            } else {
                cellIndex++;
            }
        }
        logger.info("Grid {} generation successfully", grid.getId());
        logger.debug(grid);
        return grid;
    }

    private boolean setCellValue(final int cellIndex, final Grid grid) {
        logger.debug("Setting value for cell({})", cellIndex);
        final Cell cell = grid.getCell(cellIndex);
        final List<Integer> cellValidValues = grid.getCellValidValues(cellIndex);
        do {
            final Integer value = cellValuesUtils.getRandomAvailableValue(cellValidValues);
            if (cellValidValues
                    .contains(value) && cellValuesUtils.valueCanBeUsed(cellIndex, value, grid.getCells())) {
                cell.setValue(value);
                grid.removeFromAvailableForCell(cellIndex, value);
                logger.debug("cell({}) set value {}", cellIndex, value);
                return true;
            } else {
                grid.removeFromAvailableForCell(cellIndex, value);
                logger.debug("cell({}) discard value {}", cellIndex, value);
            }
        } while (!cellValidValues.isEmpty());

        logger.debug("cell({}) no available values left", cellIndex);
        return false;
    }
}
