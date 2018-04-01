package org.xzandra.sudoku.generator;

import org.xzandra.sudoku.GridUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Sudoku cell.
 */
public class Cell {
    private static final int MIN_INDEX = 0;
    private static Random random = new Random();
    private int cellIndex;
    private int row;
    private int column;
    private int square;
    private int value;
    private List<Integer> availableValues;

    public Cell(final int cellIndex) {
        setIndices(cellIndex);
        initializeAvailableValues();
    }

    private void setIndices(final int cellIndex) {
        this.cellIndex = cellIndex;
        this.row = GridUtils.calculateRowIndex(cellIndex);
        this.column = GridUtils.calculateColumnIndex(cellIndex);
        this.square = GridUtils.calculateSquareIndex(row, column);
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

    /**
     * Sets a valid value to the sell additionally removing it from available values.
     * Nothing happens if the value if invalid.
     *
     * @param value - value to set.
     */
    public void setValue(final Integer value) {
        if (this.isValidValue(value)) {
            this.value = value;
            this.removeFromAvailable(value);
        }
    }

    /**
     * Returns random available value for the cell.
     * @return a random valid value for the sell or 0 if no valid values are available.
     */
    public int getRandomAvailableValue() {
        if (!this.hasAvailableValues()) {
            return 0;
        }

        int index = random.ints(MIN_INDEX, this.availableValues.size())
                          .limit(1)
                          .findFirst()
                          .getAsInt();
        return this.availableValues.get(index);
    }

    public boolean hasAvailableValues() {
        return !availableValues.isEmpty();
    }

    /**
     * Checks if the value is valid for the cell.
     * @param value - value to check.
     * @return true if there are available values and value to check is included, false otherwise.
     */
    public boolean isValidValue(final Integer value) {
        return this.hasAvailableValues() && this.availableValues.contains(value);
    }

    public void removeFromAvailable(final Integer value) {
        this.availableValues.remove(value);
    }

    /**
     * Sets the value of the cell to 0 and reset available values list.
     */
    public void reset() {
        this.value = 0;
        initializeAvailableValues();
    }

    @Override
    public String toString() {
        return "Cell{" + row + ", " + column + ", " + square + ", value=" + value + '}';
    }
}
