package org.xzandra.sudoku.model;

import org.xzandra.sudoku.common.RegionIndexBuilder;
import org.xzandra.sudoku.common.ValidValuesUtils;

import java.util.List;
import java.util.Objects;

/**
 * Sudoku cell.
 */
public class SudokuCell {
    private int value = 0;
    private int index;
    private int row;
    private int column;
    private int square;
    private boolean fixed;
    private List<Integer> availables;

    public SudokuCell(final int originalValue, final int index) {
        this.value = originalValue;
        this.index = index;
        this.row = RegionIndexBuilder.calculateRowIndex(this.index);
        this.column = RegionIndexBuilder.calculateColumnIndex(this.index);
        this.square = RegionIndexBuilder.calculateSquareIndex(row, column);
        this.availables = ValidValuesUtils.getDefaultAvailables();
        this.fixed = originalValue != 0;
    }

    public SudokuCell(final int index) {
        this(0, index);
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
        this.removeAvailaible(value);
    }

    public int getIndex() {
        return index;
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

    public boolean isFixed() {
        return fixed;
    }

    public List<Integer> getAvailables() {
        return availables;
    }

    public void removeAvailaible(int value) {
        this.availables.remove(Integer.valueOf(value));
    }

    public void reset() {
        this.value = 0;
        this.availables = ValidValuesUtils.getDefaultAvailables();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SudokuCell cell = (SudokuCell) o;
        return getValue() == cell.getValue() &&
                getIndex() == cell.getIndex() &&
                getRow() == cell.getRow() &&
                getColumn() == cell.getColumn() &&
                getSquare() == cell.getSquare();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getValue(), getIndex(), getRow(), getColumn(), getSquare(), isFixed());
    }

    @Override
    public String toString() {
        return "SudokuCell{" +
                "index=" + index +
                ", value=" + value +
                ", availables=" + availables +
                '}';
    }
}
