package org.xzandra.sudoku.common;

import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.model.Cell;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CellBuilderTest {
    @Test
    void buildWithDefaultValue() {
        final int index = 11;
        final Cell cell = new CellBuilder(index).build();

        assertAll(
                () -> assertEquals(index, cell.getIndex()),
                () -> assertEquals(1, cell.getRow()),
                () -> assertEquals(2, cell.getColumn()),
                () -> assertEquals(0, cell.getSquare()),
                () -> assertEquals(0, cell.getValue()));
    }

    @Test
    void buildWithSpecifiedValue() {
        final int value = 9;
        final int index = 21;
        final Cell cell = new CellBuilder(index).value(value)
                                                .build();
        assertAll(
                () -> assertEquals(index, cell.getIndex()),
                () -> assertEquals(2, cell.getRow()),
                () -> assertEquals(3, cell.getColumn()),
                () -> assertEquals(1, cell.getSquare()),
                () -> assertEquals(value, cell.getValue()));
    }
}