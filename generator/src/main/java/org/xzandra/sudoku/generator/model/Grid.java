package org.xzandra.sudoku.generator.model;

import java.util.stream.Stream;

import static org.xzandra.sudoku.generator.SudokuUtils.GRID_SIZE;
import static org.xzandra.sudoku.generator.SudokuUtils.TOTAL_CELL_SIZE;

/**
 * Sudoku grid.
 */
public class Grid {
    private final Cell[] cells = new Cell[TOTAL_CELL_SIZE];
    private final Region[] rows = Stream.generate(() -> new Region())
                                        .limit(GRID_SIZE)
                                        .toArray(Region[]::new);
    private final Region[] columns = Stream.generate(() -> new Region())
                                           .limit(GRID_SIZE)
                                           .toArray(Region[]::new);
    private final Region[] squares = Stream.generate(() -> new Region())
                                           .limit(GRID_SIZE)
                                           .toArray(Region[]::new);

    public Grid() {
        for (int cellIndex = 0; cellIndex < TOTAL_CELL_SIZE; cellIndex++) {
            final Cell cell = new Cell(cellIndex);
            cells[cellIndex] = cell;
            rows[cell.getRow()].addCell(cell);
            columns[cell.getColumn()].addCell(cell);
            squares[cell.getSquare()].addCell(cell);
        }
    }

    public void setRandomCellValue(final int cellIndex) {
        final Cell cell = cells[cellIndex];
        cell.setValue(cell.getRandomAvailableValue());
    }

    public Cell getCell(final int cellIndex) {
        return cells[cellIndex];
    }

    public boolean isValidValueForRow(final int rowIndex, final Integer value) {
        return rows[rowIndex].isValidValue(value);
    }

    public boolean isValidValueForColumn(final int columnIndex, final Integer value) {
        return columns[columnIndex].isValidValue(value);
    }

    public boolean isValidValueForSquare(final int squareIndex, final Integer value) {
        return squares[squareIndex].isValidValue(value);
    }

    @Override
    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder().append("Sudoku Grid:")
                                                                .append(System.lineSeparator());

        for (Region row : rows) {
            for (Cell cell : row.getCells()) {
                stringRepresentation.append(cell.getValue())
                                    .append("\t");
            }
            stringRepresentation.append(System.lineSeparator());
        }
        return stringRepresentation.toString();
    }
}
