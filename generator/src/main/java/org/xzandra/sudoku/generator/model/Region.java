package org.xzandra.sudoku.generator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Region of the sudoku grid: e.j. row, column, block.
 */
public class Region {
    private List<Cell> cells = new ArrayList<>();

    public void addCell(final Cell cell) {
        cells.add(cell);
    }

    public boolean isValidValue(final int value) {
        for (Cell cell : cells) {
            if (cell.getValue() == value) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Region{" + cells.stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(", ")) +
                '}';
    }

    public List<Cell> getCells() {
        return cells;
    }
}
