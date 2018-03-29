package org.xzandra.sudoku.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CellTest {
    @Test
    void cellInitialization() {
        Cell cell = new Cell(1, 2);
        assertEquals(0, cell.getValue());
        assertEquals(1, cell.getRow());
        assertEquals(2, cell.getColumn());
    }

    @Test
    void hasAvailableValues() {
        Cell cell = new Cell(1, 1);
        assertTrue(cell.hasAvailableValues());
    }

    @Test
    void getRandomAvailableValue() {
        Cell cell = new Cell(1, 1);
        assertEquals(0, cell.getValue());
        final int randomAvailableValue = cell.getRandomAvailableValue();
        assertNotEquals(0, randomAvailableValue);
    }

    @Test
    void setValidValue() {
        Cell cell = new Cell(1, 1);
        cell.setValue(5);
        assertEquals(5, cell.getValue());
        assertFalse(cell.isValidValue(5));
    }

    @Test
    void setInvalidValue() {
        Cell cell = new Cell(1, 1);
        cell.setValue(1);
        cell.setValue(5);
        assertEquals(5, cell.getValue());
        cell.setValue(1);
        assertEquals(5, cell.getValue());
    }

    @Test
    void isValidValue() {
        Cell cell = new Cell(1, 1);
        assertTrue(cell.isValidValue(5));
    }

    @Test
    void isInvalidValue() {
        Cell cell = new Cell(1, 1);
        cell.setValue(5);
        assertFalse(cell.isValidValue(cell.getValue()));
    }
}