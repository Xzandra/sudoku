package org.xzandra.sudoku.common;

import org.apache.commons.lang3.Range;

/**
 * Constants associated with sudoku grid.
 */
public final class GridConstants {
    public static final int GRID_SIZE = 9;
    public static final int TOTAL_CELL_SIZE = GRID_SIZE * GRID_SIZE;
    public static final Range<Integer> SUDOKU_RANGE = Range.between(0, GRID_SIZE - 1);
    public static final Range<Integer> SUDOKU_CELL_VALUE_RANGE = Range.between(1, GRID_SIZE);

    private GridConstants() {
    }
}
