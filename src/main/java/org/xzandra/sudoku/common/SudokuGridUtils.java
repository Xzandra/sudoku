package org.xzandra.sudoku.common;

import org.xzandra.sudoku.model.old.SudokuCellImpl;
import org.xzandra.sudoku.model.old.SudokuGrid;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper methods for sudoku grid.
 */
public final class SudokuGridUtils {
    private SudokuGridUtils() {
    }

    public static SudokuCellImpl findCellWithMinAvailables(final SudokuGrid grid) {
        return Arrays.stream(grid.getCells())
                     .filter(sudokuCell -> !sudokuCell.isFixed() && sudokuCell.getValue() == 0)
                     .min(Comparator.comparing(o -> o.getPossibilities()
                                                     .size()))
                     .orElse(null);
    }

    public static List<SudokuCellImpl> findNotSetCells(final SudokuGrid grid) {
        return Arrays.stream(grid.getCells())
                     .filter(sudokuCell -> !sudokuCell.isFixed() && sudokuCell.getValue() == 0)
                     .collect(Collectors.toList());
    }
}
