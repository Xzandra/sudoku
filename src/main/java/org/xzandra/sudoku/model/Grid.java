package org.xzandra.sudoku.model;

import org.xzandra.sudoku.GridUtils;
import org.xzandra.sudoku.common.CellBuilder;
import org.xzandra.sudoku.common.CellValuesUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.xzandra.sudoku.common.GridConstants.TOTAL_CELL_SIZE;

/**
 * Sudoku grid.
 */
public class Grid {
    private final String id = UUID.randomUUID()
                                  .toString();
    private Cell[] cells = new Cell[TOTAL_CELL_SIZE];
    private List<Integer>[] cellValidValues = new ArrayList[TOTAL_CELL_SIZE];

    public Grid() {
        IntStream.range(0, TOTAL_CELL_SIZE)
                 .forEach(cellIndex -> {
                     cells[cellIndex] = new CellBuilder(cellIndex).build();
                     cellValidValues[cellIndex] = new CellValuesUtils().getDefaultValidValues();
                 });
    }

    public Grid(Cell[] cells, List<Integer>[] cellValidValues) {
        this.cells = cells;
        this.cellValidValues = cellValidValues;
    }

    public Cell getCell(final int index) {
        return cells[index];
    }

    public void setCellValue(final int index, final int value) {
        cells[index].setValue(value);
        cellValidValues[index].remove(value);
    }

    public String getId() {
        return id;
    }

    public List<Integer> getCellValidValues(final int index) {
        return cellValidValues[index];
    }

    public List<Integer>[] getValidValues() {
        return cellValidValues;
    }

    @Override
    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder().append("Sudoku Grid ")
                                                                .append(this.id)
                                                                .append(":")
                                                                .append(System.lineSeparator());

        IntStream.range(0, GridUtils.TOTAL_CELL_SIZE)
                 .forEach(cellIndex -> {
                     final Cell cell = cells[cellIndex];
                     if (cell.getColumn() == GridUtils.GRID_SIZE - 1) {
                         stringRepresentation.append(cell.getValue())
                                             .append(System.lineSeparator());
                     } else {
                         stringRepresentation.append(cell.getValue())
                                             .append("\t");
                     }
                 });
        return stringRepresentation.toString();
    }

    public Cell[] getCells() {
        return cells;
    }
}
