package org.xzandra.sudoku.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CellTest {

    @Test
    void cellRemovableInitialization() {
        final Cell cell = new Cell(1, 2, 3, 4, 0);

        assertAll(
                () -> assertEquals(1, cell.getIndex()),
                () -> assertEquals(2, cell.getRow()),
                () -> assertEquals(3, cell.getColumn()),
                () -> assertEquals(4, cell.getSquare()),
                () -> assertEquals(0, cell.getValue()),
                () -> assertTrue(cell.isRemovable()));
    }

    @Test
    void cellNotRemovableInitialization() {
        final Cell cell = new Cell(1, 2, 3, 4, 5);

        assertAll(
                () -> assertEquals(1, cell.getIndex()),
                () -> assertEquals(2, cell.getRow()),
                () -> assertEquals(3, cell.getColumn()),
                () -> assertEquals(4, cell.getSquare()),
                () -> assertEquals(5, cell.getValue()),
                () -> assertFalse(cell.isRemovable()));
    }

}