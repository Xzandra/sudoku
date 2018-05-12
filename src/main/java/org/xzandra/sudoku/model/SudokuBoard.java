package org.xzandra.sudoku.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Sudoku Board.
 */
public class SudokuBoard {
    public static final int BOARD_SIZE = 9;
    private final String id = UUID.randomUUID()
                                  .toString();
    private SudokuCell[][] cells = new SudokuCell[BOARD_SIZE][BOARD_SIZE];
    private SudokuCell[][] transposeCells = new SudokuCell[BOARD_SIZE][BOARD_SIZE];
    private HashMap<Integer, SudokuCell[]> squares;

    public SudokuBoard(final int[] cellValues) {
        IntStream.range(0, cellValues.length)
                 .forEach(index -> {
                     final SudokuCell sudokuCell = new SudokuCell(index, cellValues[index]);
                     cells[sudokuCell.getRow()][sudokuCell.getColumn()] = sudokuCell;
                     transposeCells[sudokuCell.getColumn()][sudokuCell.getRow()] = sudokuCell;
                 });
        squares = Arrays.stream(this.cells)
                        .flatMap(Arrays::stream)
                        .collect(Collectors.groupingBy(SudokuCell::getSquare, HashMap::new,
                                SudokuBoardUtils.toArray(size -> new SudokuCell[size])));
    }

    public SudokuCell getCell(final int row, final int column) {
        return cells[row][column];
    }

    public Stream<SudokuCell> getUnfilledCellsStream() {
        return Arrays.stream(cells)
                     .flatMap(Arrays::stream)
                     .filter(sudokuCell -> !sudokuCell.isFixed() && sudokuCell.getValue() == 0);
    }

    public SudokuCell[] getRow(final int row) {
        return cells[row];
    }

    public SudokuCell[] getColumn(final int column) {
        return transposeCells[column];
    }

    public SudokuCell[] getSquare(final int square) {
        return squares.get(square);
    }

    public List<Integer> getOptions(final int row, final int column) {
        SudokuCell cell = cells[row][column];
        return getOptions(cell);
    }

    public List<Integer> getOptions(final SudokuCell cell) {
        List<Integer> options = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        options = SudokuBoardUtils.removeInvalidForLine(options, getRow(cell.getRow()));
        options = SudokuBoardUtils.removeInvalidForLine(options, getColumn(cell.getColumn()));
        options = SudokuBoardUtils.removeInvalidForLine(options, getSquare(cell.getSquare()));
        options.removeAll(cell.getCheckedOptions());
        return options;
    }

    public boolean isFilled() {
        return Arrays.stream(this.cells)
                     .flatMap(Arrays::stream)
                     .filter(sudokuCell -> sudokuCell.getValue() == 0)
                     .count() == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder().append("Sudoku SudokuBoard ")
                                                                .append(this.id)
                                                                .append(":")
                                                                .append(System.lineSeparator());

        IntStream.range(0, BOARD_SIZE)
                 .forEach(rowIndex -> {
                     final String row = Arrays.stream(this.getRow(rowIndex))
                                              .map(sudokuCell -> String.valueOf(sudokuCell.getValue()))
                                              .collect(Collectors.joining(","));
                     stringRepresentation.append(row)
                                         .append(System.lineSeparator());
                 });
        return stringRepresentation.toString();
    }
}
