package org.xzandra.sudoku.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SudokuCellTest {
    @Test
    public void createCellWithDefaultValue() {
        final SudokuCell cell = new SudokuCell(2, 12);
        assertAll(
                () -> assertEquals(12, cell.getIndex(), "cell index"),
                () -> assertEquals(1, cell.getRow(), "cell row index"),
                () -> assertEquals(3, cell.getColumn(), "cell column index"),
                () -> assertEquals(1, cell.getSquare(), "cell square index"),
                () -> assertEquals(2, cell.getValue(), "cell value"),
                () -> assertTrue(cell.isFixed(), "cell fixed"));
    }

    @Test
    public void createCellWithCustomValue() {
        final SudokuCell cell = new SudokuCell(12);

        assertAll(
                () -> assertEquals(12, cell.getIndex(), "cell index"),
                () -> assertEquals(1, cell.getRow(), "cell row index"),
                () -> assertEquals(3, cell.getColumn(), "cell column index"),
                () -> assertEquals(1, cell.getSquare(), "cell square index"),
                () -> assertEquals(0, cell.getValue(), "cell value"),
                () -> assertFalse(cell.isFixed(), "cell fixed"));
    }

    @Test
    void setValue() {
        final SudokuCell cell = new SudokuCell(12);
        assertEquals(9, cell.getAvailables()
                            .size());
        cell.setValue(7);
        assertEquals(8, cell.getAvailables()
                            .size());
        assertFalse(cell.getAvailables()
                        .contains(7));
    }
}