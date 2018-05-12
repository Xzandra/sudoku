package org.xzandra.sudoku.solver;

import org.xzandra.sudoku.model.SudokuBoard;
import org.xzandra.sudoku.model.SudokuBoardUtils;
import org.xzandra.sudoku.model.SudokuCell;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper methods for sudoku grid model.
 */
public final class SudokuGridSolverUtils {
    private SudokuGridSolverUtils() {
    }

    /**
     * Find all cells where only one value is possible.
     *
     * @param board - sudoku board
     * @return - list of cells with set option value
     */
    public static List<SudokuCell> findCellsWithSingleOption(final SudokuBoard board) {
        return board.getUnfilledCellsStream()
                    .filter(sudokuCell -> {
                        final List<Integer> options = board.getOptions(sudokuCell);
                        if (options.size() == 1) {
                            sudokuCell.setOption(options.get(0));
                            return true;
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
    }

    /**
     * Find cell with unique option because all other column or row in square have this value.
     *
     * @param board - sudoku board
     * @return - cell with set option value
     */
    public static SudokuCell findCellWithTwoOutOfThreeOption(final SudokuBoard board) {
        return board.getUnfilledCellsStream()
                    .filter(sudokuCell -> findOptionWithTwoOutOfThree(sudokuCell, board))
                    .findFirst()
                    .orElse(null);
    }

    private static boolean findOptionWithTwoOutOfThree(final SudokuCell sudokuCell, final SudokuBoard board) {
        final List<Integer> rowIndices = Arrays.stream(board.getSquare(sudokuCell.getSquare()))
                                               .mapToInt(SudokuCell::getRow)
                                               .distinct()
                                               .filter(row -> row != sudokuCell.getRow())
                                               .boxed()
                                               .collect(Collectors.toList());

        final List<Integer> columnIndices = Arrays.stream(board.getSquare(sudokuCell.getSquare()))
                                                  .mapToInt(SudokuCell::getColumn)
                                                  .distinct()
                                                  .filter(column -> column != sudokuCell.getColumn())
                                                  .boxed()
                                                  .collect(Collectors.toList());

        final Integer cellOption = board.getOptions(sudokuCell)
                                        .stream()
                                        .filter(option -> SudokuBoardUtils.isValuePresentInAllRows(option, rowIndices, board) && SudokuBoardUtils
                                                .isValuePresentInAllColumns(option, columnIndices, board))
                                        .findFirst()
                                        .orElse(0);
        sudokuCell.setOption(cellOption);
        return sudokuCell.getOption() != 0;
    }

    /**
     * Find cell with unique option because in a square there is only one place that can take this particular value.
     *
     * @param board - sudoku board
     * @return - cell with set option value
     */
    public static SudokuCell findCellWithOnlySquareOption(final SudokuBoard board) {
        return board.getUnfilledCellsStream()
                    .filter(sudokuCell -> findOptionWithOnlySquare(sudokuCell, board))
                    .findFirst()
                    .orElse(null);
    }

    private static boolean findOptionWithOnlySquare(final SudokuCell sudokuCell, final SudokuBoard board) {
        return board.getOptions(sudokuCell)
                    .stream()
                    .anyMatch(option -> {
                        final boolean cellOptionUnique = SudokuBoardUtils.isCellOptionUnique(option, sudokuCell, board);
                        if (cellOptionUnique) {
                            sudokuCell.setOption(option);
                        }
                        return cellOptionUnique;
                    });
    }

    /**
     * Find unfilled cell with min options available.
     *
     * @param board - sudoku board
     * @return - cell with  min options available
     */
    public static SudokuCell findCellWithMinOptions(final SudokuBoard board) {
        return board.getUnfilledCellsStream()
                    .min(Comparator.comparing(sudokuCell -> board.getOptions(sudokuCell)
                                                                 .size()))
                    .orElse(null);
    }
}
