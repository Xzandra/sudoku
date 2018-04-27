package org.xzandra.sudoku.common;

import org.apache.commons.lang3.Range;

import static org.xzandra.sudoku.common.GridConstants.GRID_RANGE_SIZE;
import static org.xzandra.sudoku.common.GridConstants.SUDOKU_RANGE;
import static org.xzandra.sudoku.common.GridConstants.TOTAL_GRID_SIZE;

/**
 * Helper methods for calculation index of grid region.
 */
public class RegionIndexBuilder {
    private static final Range<Integer> rangeZero = Range.between(0, 2);
    private static final Range<Integer> rangeOne = Range.between(3, 5);
    private static final Range<Integer> rangeTwo = Range.between(6, 8);
    private static final Range<Integer> rangeSudokuCell = Range.between(0, TOTAL_GRID_SIZE);

    private RegionIndexBuilder() {
    }

    public static int calculateSquareIndex(final int rowIndex, final int columnIndex) {
        if (!SUDOKU_RANGE.contains(rowIndex) || !SUDOKU_RANGE.contains(columnIndex)) {
            throw new IllegalArgumentException("invalid row or column index for sudoku grid");
        }

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

    /**
     * Calculates row index of the cell. From 0 to 8.
     *
     * @param cellIndex - index of the cell
     * @return corresponding row index
     */
    public static int calculateRowIndex(final int cellIndex) {
        if (!rangeSudokuCell.contains(cellIndex)) {
            throw new IllegalArgumentException("invalid cell index for sudoku grid");
        }
        return cellIndex / GRID_RANGE_SIZE;
    }

    /**
     * Calculates column index of the cell. From 0 to 8.
     *
     * @param cellIndex - index of the cell
     * @return corresponding column index
     */
    public static int calculateColumnIndex(final int cellIndex) {
        if (!rangeSudokuCell.contains(cellIndex)) {
            throw new IllegalArgumentException("invalid cell index for sudoku grid");
        }
        return cellIndex % GRID_RANGE_SIZE;
    }
}
