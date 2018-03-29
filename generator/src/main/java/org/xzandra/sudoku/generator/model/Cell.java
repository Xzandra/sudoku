package org.xzandra.sudoku.generator.model;

import org.xzandra.sudoku.generator.RandomGenerator;
import org.xzandra.sudoku.generator.SudokuUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Sudoku cell.
 */
public class Cell {
    private int cellIndex;
    private int row;
    private int column;
    private int square;
    private int value;
    private List<Integer> availableValues;

    public Cell(final int cellIndex) {
        this.cellIndex = cellIndex;
        this.row = SudokuUtils.calculateRowIndex(cellIndex);
        this.column = SudokuUtils.calculateColumnIndex(cellIndex);
        this.square = SudokuUtils.calculateSquareIndex(row, column);
        initializeAvailableValues();
    }

    private void initializeAvailableValues() {
        availableValues = new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
        }};
    }

    public int getCellIndex() {
        return cellIndex;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSquare() {
        return square;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final Integer value) {
        if (this.isValidValue(value)) {
            this.value = value;
            this.removeFromAvailable(value);
        }
    }

    public int getRandomAvailableValue() {
        if (!this.hasAvailableValues()) {
            return 0;
        }
        int index = RandomGenerator.getIndex(this.availableValues.size());
        return this.availableValues.get(index);
    }

    public boolean hasAvailableValues() {
        return !availableValues.isEmpty();
    }

    public boolean isValidValue(final Integer value) {
        return this.hasAvailableValues() && this.availableValues.contains(value);
    }

    public void removeFromAvailable(final Integer value) {
        this.availableValues.remove(value);
    }

    public void reset() {
        this.value = 0;
        initializeAvailableValues();
    }

    @Override
    public String toString() {
        return "Cell{" + row +
                ", " + column +
                ", '" + square + '\'' +
                ", value=" + value +
                '}';
    }
}
