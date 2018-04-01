package org.xzandra.sudoku;

import org.apache.commons.lang3.Range;

/**
 * Helper methods for grid.
 */
public final class GridUtils {
    public static final int GRID_SIZE = 9;
    public static final int TOTAL_CELL_SIZE = GRID_SIZE * GRID_SIZE;
    private static final Range<Integer> rangeZero = Range.between(0, 2);
    private static final Range<Integer> rangeOne = Range.between(3, 5);
    private static final Range<Integer> rangeTwo = Range.between(6, 8);

    private GridUtils() {
    }

    public static int calculateSquareIndex(final int rowIndex, final int columnIndex) {
        if (rangeZero.contains(rowIndex) && rangeZero.contains(columnIndex)) {
            return 0;
        } else if (rangeZero.contains(rowIndex) && rangeOne.contains(columnIndex)) {
            return 1;
        } else if (rangeZero.contains(rowIndex) && rangeTwo.contains(columnIndex)) {
            return 2;
        } else if (rangeOne.contains(rowIndex) && rangeZero.contains(columnIndex)) {
            return 3;
        } else if (rangeOne.contains(rowIndex) && rangeOne.contains(columnIndex)) {
            return 4;
        } else if (rangeOne.contains(rowIndex) && rangeTwo.contains(columnIndex)) {
            return 5;
        } else if (rangeTwo.contains(rowIndex) && rangeZero.contains(columnIndex)) {
            return 6;
        } else if (rangeTwo.contains(rowIndex) && rangeOne.contains(columnIndex)) {
            return 7;
        }
        return 8;
    }

    public static int calculateRowIndex(final int cellIndex) {
        return cellIndex / GRID_SIZE;
    }

    public static int calculateColumnIndex(final int cellIndex) {
        return cellIndex % GRID_SIZE;
    }
}
