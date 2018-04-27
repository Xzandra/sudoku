package org.xzandra.sudoku.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.xzandra.sudoku.common.GridConstants.GRID_RANGE_SIZE;
import static org.xzandra.sudoku.common.GridConstants.TOTAL_GRID_SIZE;

class RegionIndexBuilderTest {
    @Test
    void calculateSquareIndex() {
        assertEquals(0, RegionIndexBuilder.calculateSquareIndex(0, 0));
        assertEquals(1, RegionIndexBuilder.calculateSquareIndex(0, 3));
        assertEquals(8, RegionIndexBuilder.calculateSquareIndex(GRID_RANGE_SIZE - 1, GRID_RANGE_SIZE - 1));
    }

    @Test
    void calculateSquareIndexFromInvalidRowIndex() {
        assertThrows(IllegalArgumentException.class, () -> {
            RegionIndexBuilder.calculateSquareIndex(9, 0);
        });
    }

    @Test
    void calculateSquareIndexFromInvalidColumnIndex() {
        assertThrows(IllegalArgumentException.class, () -> {
            RegionIndexBuilder.calculateSquareIndex(1, 12);
        });
    }

    @Test
    void calculateRowIndex() {
        assertEquals(0, RegionIndexBuilder.calculateRowIndex(0));
        assertEquals(2, RegionIndexBuilder.calculateRowIndex(21));
        assertEquals(8, RegionIndexBuilder.calculateRowIndex(TOTAL_GRID_SIZE - 1));
    }

    @Test
    void calculateRowIndexFromInvalidCellIndex() {
        assertThrows(IllegalArgumentException.class, () -> {
            RegionIndexBuilder.calculateRowIndex(TOTAL_GRID_SIZE + 1);
        });
    }

    @Test
    void calculateColumnIndex() {
        assertEquals(0, RegionIndexBuilder.calculateColumnIndex(0));
        assertEquals(3, RegionIndexBuilder.calculateColumnIndex(21));
        assertEquals(8, RegionIndexBuilder.calculateColumnIndex(TOTAL_GRID_SIZE - 1));
    }

    @Test
    void calculateColumnIndexFromInvalidCellIndex() {
        assertThrows(IllegalArgumentException.class, () -> {
            RegionIndexBuilder.calculateColumnIndex(TOTAL_GRID_SIZE + 1);
        });
    }
}