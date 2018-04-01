package org.xzandra.sudoku.common;

import org.xzandra.sudoku.model.Cell;

/**
 * Builder class for sudoku cell creation.
 */
public final class CellBuilder {
    private int index;
    private int value = 0;

    public CellBuilder(final int index) {
        this.index = index;
    }

    public CellBuilder value(final int value) {
        this.value = value;
        return this;
    }

    public Cell build() {
        int row = RegionIndexBuilder.calculateRowIndex(this.index);
        int column = RegionIndexBuilder.calculateColumnIndex(this.index);
        int square = RegionIndexBuilder.calculateSquareIndex(row, column);
        return new Cell(index, row, column, square, value);
    }
}