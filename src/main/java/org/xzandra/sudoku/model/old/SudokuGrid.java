package org.xzandra.sudoku.model.old;

import org.xzandra.sudoku.common.ValidValuesUtils;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.xzandra.sudoku.common.GridConstants.GRID_RANGE_SIZE;
import static org.xzandra.sudoku.common.GridConstants.TOTAL_GRID_SIZE;

/**
 * Sudoku grid.
 */
public class SudokuGrid {
    private final String id = UUID.randomUUID()
                                  .toString();
    private SudokuCellImpl[] cells = new SudokuCellImpl[TOTAL_GRID_SIZE];

    public SudokuGrid() {
        IntStream.range(0, TOTAL_GRID_SIZE)
                 .forEach(cellIndex -> cells[cellIndex] = new SudokuCellImpl(cellIndex));
    }

    public String getId() {
        return id;
    }

    public SudokuCellImpl[] getCells() {
        return cells;
    }

    public SudokuCellImpl getCell(final int index) {
        return cells[index];
    }

    public void setCell(final int index, final SudokuCellImpl cell) {
        this.cells[index] = cell;
    }

    public void updateCellPossibilities() {
        IntStream.range(0, TOTAL_GRID_SIZE)
                 .parallel()
                 .forEach(cellIndex -> IntStream.range(0, this.getCell(cellIndex)
                                                              .getPossibilities()
                                                              .size())
                                                .forEach(possibleValueIndex -> {
                                                    final Integer possibleValue = this.getCell(cellIndex)
                                                                                      .getPossibilities()
                                                                                      .get(possibleValueIndex);
                                                    if (!ValidValuesUtils.isValidValue(cellIndex, possibleValue, this)) {
                                                        this.getCell(cellIndex)
                                                            .removePossibility(possibleValue);
                                                    }
                                                }));
    }

    public boolean isSolved() {
        return Arrays.stream(this.getCells())
                     .filter(sudokuCell -> !sudokuCell.isFixed() && sudokuCell.getValue() == 0)
                     .count() == 0;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getCells());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SudokuGrid grid = (SudokuGrid) o;
        return Arrays.equals(getCells(), grid.getCells());
    }

    @Override
    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder().append("Sudoku SudokuBoard ")
                                                                .append(this.id)
                                                                .append(":")
                                                                .append(System.lineSeparator());

        IntStream.range(0, TOTAL_GRID_SIZE)
                 .forEach(cellIndex -> {
                     final SudokuCellImpl cell = cells[cellIndex];
                     if (cell.getColumn() == GRID_RANGE_SIZE - 1) {
                         stringRepresentation.append(cell.getValue())
                                             .append(System.lineSeparator());
                     } else {
                         stringRepresentation.append(cell.getValue())
                                             .append("\t");
                     }
                 });
        return stringRepresentation.toString();
    }
}
