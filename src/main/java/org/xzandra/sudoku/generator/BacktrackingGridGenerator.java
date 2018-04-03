package org.xzandra.sudoku.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.xzandra.sudoku.common.GridConstants.TOTAL_CELL_SIZE;

/**
 * Generator of the sudoku grid: backtracking algorithm.
 */
public class BacktrackingGridGenerator {
    private static final Logger logger = LogManager.getLogger(BacktrackingGridGenerator.class);

    public Grid generate() throws Exception {
        final Grid grid = new Grid();

        logger.info("Grid {} generation started", grid.getId());

        int cellIndex = 0;
        while (cellIndex < TOTAL_CELL_SIZE) {
            final boolean cellValueSet = setCellValue(cellIndex, grid);
            if (!cellValueSet) {
                grid.getCell(cellIndex--)
                    .reset();
                logger.debug("Going back to cell({})", cellIndex);
                final int currentValue = grid.getCell(cellIndex)
                                             .getValue();
                grid.getCell(cellIndex)
                    .removeFromAvailable(currentValue);
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
        do {
            final Integer value = cell.getRandomAvailableValue();
            if (canUseValue(cell.getCellIndex(), grid, value)) {
                cell.setValue(value);
                logger.debug("cell({}) set value {}", cellIndex, value);
                return true;
            } else {
                cell.removeFromAvailable(value);
                logger.debug("cell({}) discard value {}", cellIndex, value);
            }
        } while (cell.hasAvailableValues());

        logger.debug("cell({}) no available values left", cellIndex);
        return false;
    }

    private boolean canUseValue(final int cellIndex, final Grid grid, final Integer value) {
        final Cell cell = grid.getCell(cellIndex);
        return cell.isValidValue(value) && grid.isValidValueForSquare(cell.getSquare(), value) && grid.isValidValueForRow(cell.getRow(),
                value) && grid
                .isValidValueForColumn(cell.getColumn(), value);
    }
}
