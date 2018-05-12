package org.xzandra.sudoku.model.old;

import org.xzandra.sudoku.common.RegionIndexBuilder;
import org.xzandra.sudoku.common.ValidValuesUtils;

import java.util.List;
import java.util.Objects;

/**
 * Sudoku cell.
 */
public class SudokuCellImpl {
    private int value = 0;
    private int index;
    private int row;
    private int column;
    private int square;
    private boolean fixed;
    private List<Integer> possibilities;

    public SudokuCellImpl(final int index, final int originalValue) {
        this.value = originalValue;
        this.index = index;
        this.row = RegionIndexBuilder.calculateRowIndex(this.index);
        this.column = RegionIndexBuilder.calculateColumnIndex(this.index);
        this.square = RegionIndexBuilder.calculateSquareIndex(row, column);
        this.possibilities = ValidValuesUtils.getDefaultAvailables();
        this.fixed = originalValue != 0;
    }

    public SudokuCellImpl(final int index) {
        this(index, 0);
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
        this.removePossibility(value);
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

    public List<Integer> getPossibilities() {
        return possibilities;
    }

    public void removePossibility(int value) {
        this.possibilities.remove(Integer.valueOf(value));
    }

    public void reset() {
        this.value = 0;
        this.possibilities = ValidValuesUtils.getDefaultAvailables();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getValue(), getIndex(), getRow(), getColumn(), getSquare(), isFixed());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SudokuCellImpl cell = (SudokuCellImpl) o;
        return getValue() == cell.getValue() &&
                getIndex() == cell.getIndex() &&
                getRow() == cell.getRow() &&
                getColumn() == cell.getColumn() &&
                getSquare() == cell.getSquare();
    }

    @Override
    public String toString() {
        return "SudokuCellImpl{" +
                "index=" + index +
                ", value=" + value +
                ", possibilities=" + possibilities +
                '}';
    }
}
