package org.xzandra.sudoku.generator;

import org.xzandra.sudoku.generator.model.Cell;
import org.xzandra.sudoku.generator.model.Grid;

import static org.xzandra.sudoku.generator.SudokuUtils.TOTAL_CELL_SIZE;

/**
 * Generator of the sudoku grid.
 */
public class GridGenerator {
    public Grid generate() throws Exception {
        final Grid grid = new Grid();
        System.out.println("Starting grid initialization");

        int cellIndex = 0;
        while (cellIndex < TOTAL_CELL_SIZE) {
            final boolean cellValueSet = setCellValue(cellIndex, grid);
            if (!cellValueSet) {
                grid.getCell(cellIndex--)
                    .reset();
                System.out.println("Going back to cell(" + cellIndex + ")");
                final int currentValue = grid.getCell(cellIndex)
                                             .getValue();
                grid.getCell(cellIndex)
                    .removeFromAvailable(currentValue);
                if (cellIndex < 0) {
                    System.out.println("Grid initialization failed");
                    throw new Exception("Error initializing grid");
                }
            } else {
                cellIndex++;
            }
        }
        System.out.println("Grid initialized successfully");
        System.out.println(grid.toString());
        return grid;
    }

    private boolean setCellValue(final int cellIndex, final Grid grid) {
        System.out.println("Setting value for cell(" + cellIndex + ")");
        //System.out.println(grid.toString());
        final Cell cell = grid.getCell(cellIndex);
        do {
            final Integer value = cell.getRandomAvailableValue();
            if (canUseValue(cell.getCellIndex(), grid, value)) {
                cell.setValue(value);
                System.out.println("cell(" + cellIndex + ") set value " + value);
                return true;
            } else {
                cell.removeFromAvailable(value);
                System.out.println("cell(" + cellIndex + ") discard value " + value);
            }
        } while (cell.hasAvailableValues());

        System.out.println("cell(" + cellIndex + ") no available values left");
        return false;
    }

    private boolean canUseValue(final int cellIndex, final Grid grid, final Integer value) {
        final Cell cell = grid.getCell(cellIndex);
        return cell.isValidValue(value) && grid.isValidValueForSquare(cell.getSquare(), value) && grid.isValidValueForRow(cell.getRow(),
                value) && grid
                .isValidValueForColumn(cell.getColumn(), value);
    }
}
