package org.xzandra.sudoku.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Sudoku cell.
 */
public class Cell {
    private int row;
    private int column;
    private int value;
    private List<Integer> availableValues;

    public Cell(final int row, final int column) {
        this.row = row;
        this.column = column;
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

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        if (this.isValidValue(value)) {
            this.value = value;
            this.availableValues.remove(value);
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

    public boolean isValidValue(Integer value) {
        return this.availableValues.contains(value);
    }
}
