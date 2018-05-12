package org.xzandra.sudoku.model;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.common.GridLoader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.xzandra.sudoku.model.SudokuBoard.BOARD_SIZE;

class SudokuBoardUtilsTest {
    private static SudokuBoard sudokuBoard;
    private final SudokuCell[] line = setUpLine();

    @BeforeAll
    public static void setUp() throws URISyntaxException {
        final URI sudokuPath = SudokuBoardTest.class.getClassLoader()
                                                    .getResource("sudokutables/loader/sudoku_table_solvable.csv")
                                                    .toURI();
        final int[] cells = new GridLoader().loadCellsFromCsv(sudokuPath);
        sudokuBoard = new SudokuBoard(cells);
    }

    @Test
    public void removeInvalidForLine() {
        List<Integer> options = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        final ArrayList<Integer> expectedOptions = new ArrayList<>(Arrays.asList(2, 5, 6, 8));

        options = SudokuBoardUtils.removeInvalidForLine(options, line);

        Assertions.assertTrue(CollectionUtils.isEqualCollection(expectedOptions, options));
    }

    @Test
    public void isValuePresentInLine() {
        Assertions.assertTrue(SudokuBoardUtils.isValuePresentInLine(1, line));
        Assertions.assertFalse(SudokuBoardUtils.isValuePresentInLine(5, line));
    }

    @Test
    public void isValuePresentInRows() {
        List<Integer> rowIndices = new ArrayList<>(Arrays.asList(1, 2));
        Assertions.assertTrue(SudokuBoardUtils.isValuePresentInAllRows(3, rowIndices, sudokuBoard));
        Assertions.assertFalse(SudokuBoardUtils.isValuePresentInAllRows(5, rowIndices, sudokuBoard));
        Assertions.assertFalse(SudokuBoardUtils.isValuePresentInAllRows(9, rowIndices, sudokuBoard));
    }

    @Test
    public void isValuePresentInColumns() {
        List<Integer> columnIndices = new ArrayList<>(Arrays.asList(1, 2));
        Assertions.assertTrue(SudokuBoardUtils.isValuePresentInAllColumns(2, columnIndices, sudokuBoard));
        Assertions.assertFalse(SudokuBoardUtils.isValuePresentInAllColumns(7, columnIndices, sudokuBoard));
        Assertions.assertFalse(SudokuBoardUtils.isValuePresentInAllColumns(5, columnIndices, sudokuBoard));
    }

    private SudokuCell[] setUpLine() {
        final SudokuCell[] line = new SudokuCell[BOARD_SIZE];
        line[0] = new SudokuCell(0, 1);
        line[1] = new SudokuCell(1, 0);
        line[2] = new SudokuCell(2, 0);
        line[3] = new SudokuCell(3, 3);
        line[4] = new SudokuCell(4, 7);
        line[5] = new SudokuCell(5, 0);
        line[6] = new SudokuCell(6, 4);
        line[7] = new SudokuCell(7, 0);
        line[8] = new SudokuCell(8, 9);
        return line;
    }
}