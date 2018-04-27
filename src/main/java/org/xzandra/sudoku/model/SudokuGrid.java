package org.xzandra.sudoku.model;

import org.xzandra.sudoku.GridUtils;
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
    private SudokuCell[] cells = new SudokuCell[TOTAL_GRID_SIZE];

    public SudokuGrid() {
        IntStream.range(0, TOTAL_GRID_SIZE)
                 .forEach(cellIndex -> cells[cellIndex] = new SudokuCell(cellIndex));
    }

    public String getId() {
        return id;
    }

    public SudokuCell[] getCells() {
        return cells;
    }

    public SudokuCell getCell(final int index) {
        return cells[index];
    }

    public void setCell(final int index, final SudokuCell cell) {
        this.cells[index] = cell;
    }

    public void updateCellAvailables() {
        IntStream.range(0, TOTAL_GRID_SIZE)
                 .parallel()
                 .forEach(index -> IntStream.range(1, GRID_RANGE_SIZE + 1)
                                            .forEach(available -> {
                                                if (!ValidValuesUtils.isValidValue(index, available, this)) {
                                                    this.getCell(index)
                                                        .removeAvailaible(available);
                                                }
                                            }));
    }

    public boolean isSolved() {
        return Arrays.stream(this.getCells())
                     .filter(sudokuCell -> !sudokuCell.isFixed() && sudokuCell.getValue() == 0)
                     .count() == 0;
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
    public int hashCode() {
        return Arrays.hashCode(getCells());
    }

    @Override
    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder().append("Sudoku Grid ")
                                                                .append(this.id)
                                                                .append(":")
                                                                .append(System.lineSeparator());

        IntStream.range(0, GridUtils.TOTAL_CELL_SIZE)
                 .forEach(cellIndex -> {
                     final SudokuCell cell = cells[cellIndex];
                     if (cell.getColumn() == GridUtils.GRID_SIZE - 1) {
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
