package org.xzandra.sudoku.common;

import org.xzandra.sudoku.model.old.SudokuCellImpl;
import org.xzandra.sudoku.model.old.SudokuGrid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.xzandra.sudoku.common.GridConstants.GRID_RANGE_SIZE;
import static org.xzandra.sudoku.common.GridConstants.TOTAL_GRID_SIZE;

/**
 * Helper methods for values of the sudoku cells.
 */
public final class ValidValuesUtils {
    private static Random random = new Random();

    private ValidValuesUtils() {
    }

    public static ArrayList<Integer> getDefaultAvailables() {
        final ArrayList<Integer> values = new ArrayList<>();
        IntStream.range(1, GRID_RANGE_SIZE + 1)
                 .forEach(values::add);

        return values;
    }

    public static boolean isValidForRow(final int rowIndex, final Integer value, final SudokuGrid grid) {
        return !IntStream.range(0, TOTAL_GRID_SIZE)
                         .anyMatch(index -> grid.getCell(index)
                                                .getRow() == rowIndex && grid.getCell(index)
                                                                             .getValue() == value);
    }

    public static boolean isValidForColumn(final int columnIndex, final Integer value, final SudokuGrid grid) {
        return !IntStream.range(0, TOTAL_GRID_SIZE)
                         .anyMatch(index -> grid.getCell(index)
                                                .getColumn() == columnIndex && grid.getCell(index)
                                                                                   .getValue() == value);
    }

    public static boolean isValidForSquare(final int squareIndex, final Integer value, final SudokuGrid grid) {
        return !IntStream.range(0, TOTAL_GRID_SIZE)
                         .anyMatch(index -> grid.getCell(index)
                                                .getSquare() == squareIndex && grid.getCell(index)
                                                                                   .getValue() == value);
    }

    public static boolean isValidValue(final int index, final Integer value, final SudokuGrid grid) {
        final SudokuCellImpl cell = grid.getCell(index);
        return cell.getValue() != value
                && isValidForRow(cell.getRow(), value, grid)
                && isValidForColumn(cell.getColumn(), value, grid)
                && isValidForSquare(cell.getSquare(), value, grid);
    }

    /**
     * Returns random available value for the cell.
     *
     * @return a random valid value for the sell or 0 if no valid values are available.
     */
    public static int getRandomAvailableValue(final List<Integer> availableValues) {
        if (availableValues.isEmpty()) {
            return 0;
        }

        int index = random.ints(0, availableValues.size())
                          .limit(1)
                          .findFirst()
                          .getAsInt();
        return availableValues.get(index);
    }
}
