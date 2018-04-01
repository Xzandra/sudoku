package org.xzandra.sudoku.generator;

import org.xzandra.sudoku.GridUtils;

import java.util.UUID;
import java.util.stream.IntStream;

/**
 * Sudoku grid.
 */
public class Grid {
    private final Cell[] cells = new Cell[GridUtils.TOTAL_CELL_SIZE];
    private final String id = UUID.randomUUID()
                                  .toString();

    public Grid() {
        IntStream.range(0, GridUtils.TOTAL_CELL_SIZE)
                 .forEach(cellIndex -> {
                     final Cell cell = new Cell(cellIndex);
                     cells[cellIndex] = cell;
                 });
    }

    public void setRandomCellValue(final int cellIndex) {
        final Cell cell = cells[cellIndex];
        cell.setValue(cell.getRandomAvailableValue());
    }

    public Cell getCell(final int cellIndex) {
        return cells[cellIndex];
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

    public String getId() {
        return id;
    }
}
