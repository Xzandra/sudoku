package org.xzandra.sudoku.model;

import org.xzandra.sudoku.common.RegionIndexBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Sudoku cell.
 */
public class SudokuCell {
    private int value = 0;
    private int option = 0;
    private int row;
    private int column;
    private int square;
    private boolean fixed;
    private List<Integer> checkedOptions = new ArrayList<>();

    public SudokuCell(final int index, final int originalValue) {
        this.value = originalValue;
        this.row = RegionIndexBuilder.calculateRowIndex(index);
        this.column = RegionIndexBuilder.calculateColumnIndex(index);
        this.square = RegionIndexBuilder.calculateSquareIndex(row, column);
        this.fixed = originalValue != 0;
    }

    public SudokuCell(final int index) {
        this(index, 0);
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        if (!isFixed()) {
            this.value = value;
        }
    }

    public int getOption() {
        return option;
    }

    public void setOption(final int option) {
        this.option = option;
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

    public List<Integer> getCheckedOptions() {
        return checkedOptions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getRow(), getColumn(), getSquare(), isFixed());
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
                getRow() == cell.getRow() &&
                getColumn() == cell.getColumn() &&
                getSquare() == cell.getSquare() &&
                isFixed() == cell.isFixed();
    }
}
