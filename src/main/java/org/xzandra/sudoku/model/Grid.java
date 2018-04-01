package org.xzandra.sudoku.model;

import org.xzandra.sudoku.GridUtils;
import org.xzandra.sudoku.common.CellBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.xzandra.sudoku.common.GridConstants.TOTAL_CELL_SIZE;

/**
 * Sudoku grid.
 */
public class Grid {
    private final Cell[] cells = new Cell[TOTAL_CELL_SIZE];
    private final List<Integer>[] cellValidValues = new ArrayList[TOTAL_CELL_SIZE];
    private final String id = UUID.randomUUID()
                                  .toString();

    public Grid() {
        IntStream.range(0, TOTAL_CELL_SIZE)
                 .forEach(cellIndex -> {
                     cells[cellIndex] = new CellBuilder(cellIndex).build();
                     cellValidValues[cellIndex] = new ArrayList();
                 });
    }

    public Cell getCell(final int index) {
        return cells[index];
    }

    public List<Integer> getCellValidValues(final int index) {
        return cellValidValues[index];
    }

    public String getId() {
        return id;
    }

    public boolean isValidValueForRow(final int rowIndex, final Integer value) {
        for (Cell cell : cells) {
            if (cell.getRow() == rowIndex && cell.getValue() == value) {
                return false;
            }
        }

        return true;
    }

    public boolean isValidValueForColumn(final int columnIndex, final Integer value) {
        for (Cell cell : cells) {
            if (cell.getColumn() == columnIndex && cell.getValue() == value) {
                return false;
            }
        }

        return true;
    }

    public boolean isValidValueForSquare(final int squareIndex, final Integer value) {
        for (Cell cell : cells) {
            if (cell.getSquare() == squareIndex && cell.getValue() == value) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder().append("Sudoku Grid ")
                                                                .append(this.id)
                                                                .append(":")
                                                                .append(System.lineSeparator());

        IntStream.range(0, GridUtils.TOTAL_CELL_SIZE)
                 .forEach(cellIndex -> {
                     final Cell cell = cells[cellIndex];
                     if (cell.getColumn() == GridUtils.GRID_SIZE - 1) {
                         stringRepresentation.append(System.lineSeparator());
                     } else {
                         stringRepresentation.append(cell.getValue())
                                             .append("\t");
                     }
                 });
        return stringRepresentation.toString();
    }
}
