package org.xzandra.sudoku.generator;

import org.apache.commons.lang3.Range;

/**
 * Sudoku grid.
 */
public class Grid {
    public static final int GRID_SIZE = 9;
    private Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];

    public Grid() {
        initialize();
    }

    private void initialize() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                cells[row][column] = new Cell(row, column);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder().append("Sudoku Grid:")
                                                                .append(System.lineSeparator());

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                stringRepresentation.append(cell.getValue())
                                    .append("\t");
            }
            stringRepresentation.append(System.lineSeparator());
        }
        return stringRepresentation.toString();
    }

    public void setRandomCellValue(int rowIndex, int columnIndex) {
        final Cell cell = cells[rowIndex][columnIndex];
        cell.setValue(cell.getRandomAvailableValue());
    }

    public int getCellValue(int rowIndex, int columnIndex) {
        return cells[rowIndex][columnIndex].getValue();
    }

    public boolean isValidValueForRow(int rowIndex, int value) {
        Cell[] row = this.cells[rowIndex];
        for (int columnIndex = 0; columnIndex < GRID_SIZE; columnIndex++) {
            Cell cell = row[columnIndex];
            if (!cell.hasAvailableValues() || !cell.isValidValue(value)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidValueForColumn(int columnIndex, int value) {
        for (int rowIndex = 0; rowIndex < GRID_SIZE; rowIndex++) {
            Cell cell = cells[rowIndex][columnIndex];
            if (!cell.hasAvailableValues() || !cell.isValidValue(value)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidValueForBlock(int rowIndex, int columnIndex, int value) {
        final Range<Integer> rangeFirst = Range.between(0, 2);
        final Range<Integer> rangeSecond = Range.between(3, 5);
        final Range<Integer> rangeThird = Range.between(6, 8);

        Range<Integer> rowRange = rangeFirst;
        Range<Integer> columnRange = rangeFirst;

        if (rangeSecond.contains(rowIndex)) {
            rowRange = rangeSecond;
        } else if (rangeThird.contains(rowIndex)) {
            rowRange = rangeThird;
        }

        if (rangeSecond.contains(columnIndex)) {
            columnRange = rangeSecond;
        } else if (rangeThird.contains(columnIndex)) {
            columnRange = rangeThird;
        }
        return isValidValueForBlock(rowRange, columnRange, value);
    }

    private boolean isValidValueForBlock(Range<Integer> rowRange, Range<Integer> columnRange, int value) {
        for (int rowIndex = rowRange.getMinimum(); rowIndex <= rowRange.getMaximum(); rowIndex++) {
            for (int columnIndex = columnRange.getMinimum(); columnIndex <= columnRange.getMaximum(); columnIndex++) {
                Cell cell = this.cells[rowIndex][columnIndex];
                if (!cell.hasAvailableValues() || !cell.isValidValue(value)) {
                    return false;
                }
            }
        }
        return true;
    }
}
