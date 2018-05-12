package org.xzandra.sudoku.model;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Helper methods for sudoku board.
 */
public final class SudokuBoardUtils {
    private SudokuBoardUtils() {
    }

    public static List<Integer> removeInvalidForLine(final List<Integer> options, final SudokuCell[] line) {
        return options.stream()
                      .filter(option ->
                              !isValuePresentInLine(option, line))
                      .collect(Collectors.toList());
    }

    public static boolean isValuePresentInLine(final Integer value, final SudokuCell[] line) {
        return Arrays.stream(line)
                     .anyMatch(lineCell -> lineCell.getValue() == value);
    }

    public static <T> Collector<T, ?, T[]> toArray(IntFunction<T[]> converter) {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> list.toArray(converter.apply(list.size())));
    }

    public static boolean isValuePresentInAllRows(final Integer value, final List<Integer> rowIndices, final SudokuBoard board) {
        return rowIndices.stream()
                         .filter(row -> isValuePresentInLine(value, board.getRow(row)))
                         .count() == rowIndices.size();
    }

    public static boolean isValuePresentInAllColumns(final Integer value, final List<Integer> columnIndices, final SudokuBoard board) {
        return columnIndices.stream()
                            .filter(column -> isValuePresentInLine(value, board.getColumn(column)))
                            .count() == columnIndices.size();
    }

    public static boolean isCellOptionContainsInLine(final Integer value, SudokuCell cell, final SudokuCell[] line, SudokuBoard board) {
        return Arrays.stream(line)
                     .filter(sudokuCell -> !sudokuCell.equals(cell))
                     .filter(sudokuCell -> !sudokuCell.isFixed() && sudokuCell.getValue() == 0)
                     .anyMatch(lineCell -> board.getOptions(lineCell)
                                                .contains(value));
    }

    public static boolean isCellOptionUnique(final Integer option, SudokuCell cell, SudokuBoard board) {
        return !(SudokuBoardUtils.isCellOptionContainsInLine(option, cell, board.getRow(cell.getRow()), board)
                && SudokuBoardUtils.isCellOptionContainsInLine(option, cell, board.getColumn(cell.getColumn()), board)
                && SudokuBoardUtils.isCellOptionContainsInLine(option, cell, board.getSquare(cell.getSquare()), board));
    }
}
