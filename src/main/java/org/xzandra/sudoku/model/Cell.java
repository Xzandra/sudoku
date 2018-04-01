package org.xzandra.sudoku.model;

/**
 * Sudoku cell.
 */
public class Cell {
    private int index;
    private int row;
    private int column;
    private int square;
    private int value;
    private boolean removable = false;

    public Cell(final int index, final int row, final int column, final int square, final int value) {
        this.index = index;
        this.row = row;
        this.column = column;
        this.square = square;
        this.value = value;
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

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(final boolean removable) {
        this.removable = removable;
    }
}
