package org.xzandra.sudoku.common;

import org.apache.commons.lang3.Range;

/**
 * Constants associated with sudoku grid.
 */
public final class GridConstants {
    public static final int GRID_SIZE = 9;
    public static final int GRID_RANGE_SIZE = 9;
    public static final int TOTAL_GRID_SIZE = GRID_RANGE_SIZE * GRID_RANGE_SIZE;
    public static final Range<Integer> SUDOKU_RANGE = Range.between(0, GRID_RANGE_SIZE - 1);
    public static final Range<Integer> SUDOKU_CELL_VALUE_RANGE = Range.between(1, GRID_RANGE_SIZE);

    private GridConstants() {
    }
}
