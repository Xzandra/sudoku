package org.xzandra.sudoku.common;

import org.apache.commons.lang3.Range;

/**
 * Constants associated with sudoku grid.
 */
public final class GridConstants {
    public static final int GRID_SIZE = 9;
    public static final int TOTAL_CELL_SIZE = GRID_SIZE * GRID_SIZE;
    public static final Range<Integer> SUDOKU_RANGE = Range.between(0, 8);

    private GridConstants() {
    }
}
