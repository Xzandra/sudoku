package org.xzandra.sudoku.common;

import org.xzandra.sudoku.model.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.xzandra.sudoku.common.GridConstants.GRID_SIZE;
import static org.xzandra.sudoku.common.GridConstants.SUDOKU_RANGE;
import static org.xzandra.sudoku.common.GridConstants.TOTAL_CELL_SIZE;

/**
 * Helper methods for values of the sudoku cells.
 */
public class CellValuesUtils {
    public ArrayList<Integer> getDefaultValidValues() {
        final ArrayList<Integer> values = new ArrayList<>();
        IntStream.range(1, GRID_SIZE + 1)
                 .forEach(values::add);

        return values;
    }

    @SuppressWarnings("unchecked")
    public List<Integer>[] initializeValidValuesForCells(final Cell[] cells) {
        return IntStream.range(0, TOTAL_CELL_SIZE)
                        .mapToObj(index -> {
                            Cell cell = cells[index];

                            if (cell.isRemovable()) {
                                return (ArrayList<Integer>) getDefaultValidValues().parallelStream()
                                                                                   .filter(value ->
                                                                                           cell.getValue() != value
                                                                                                   && isValidForRow(cell.getRow(), value, cells)
                                                                                                   && isValidForColumn(cell.getColumn(), value, cells)
                                                                                                   && isValidForSquare(cell.getSquare(),
                                                                                                   value, cells))
                                                                                   .collect(Collectors.toList());
                            }
                            return new ArrayList<>();
                        })
                        .toArray(size -> new ArrayList[TOTAL_CELL_SIZE]);
    }

    public boolean isValidForRow(final int rowIndex, final Integer value, final Cell[] cells) {
        if (!SUDOKU_RANGE.contains(value)) {
            return false;
        }
        for (Cell cell : cells) {
            if (cell.getRow() == rowIndex && cell.getValue() == value) {
                return false;
            }
        }

        return true;
    }

    public boolean isValidForColumn(final int columnIndex, final Integer value, final Cell[] cells) {
        if (!SUDOKU_RANGE.contains(value)) {
            return false;
        }
        for (Cell cell : cells) {
            if (cell.getColumn() == columnIndex && cell.getValue() == value) {
                return false;
            }
        }

        return true;
    }

    public boolean isValidForSquare(final int squareIndex, final Integer value, final Cell[] cells) {
        if (!SUDOKU_RANGE.contains(value)) {
            return false;
        }
        for (Cell cell : cells) {
            if (cell.getSquare() == squareIndex && cell.getValue() == value) {
                return false;
            }
        }

        return true;
    }
}
