package org.xzandra.sudoku.common;

import org.xzandra.sudoku.model.SudokuCell;
import org.xzandra.sudoku.model.SudokuGrid;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Helper methods for sudoku grid.
 */
public final class SudokuGridUtils {
    private SudokuGridUtils() {
    }

    public static SudokuCell findCellWithMinAvailables(final SudokuGrid grid) {
        return Arrays.stream(grid.getCells())
                     .filter(sudokuCell -> !sudokuCell.isFixed() && sudokuCell.getValue() == 0)
                     .min(Comparator.comparing(o -> o.getAvailables()
                                                     .size()))
                     .orElse(null);
    }

    public static SudokuCell findNotSetCells(final SudokuGrid grid) {
        return Arrays.stream(grid.getCells())
                     .filter(sudokuCell -> !sudokuCell.isFixed() && sudokuCell.getValue() == 0)
                     .min(Comparator.comparing(o -> o.getAvailables()
                                                     .size()))
                     .orElse(null);
    }
}
