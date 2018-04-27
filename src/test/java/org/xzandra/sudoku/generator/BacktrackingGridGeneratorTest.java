package org.xzandra.sudoku.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.model.SudokuGrid;

class BacktrackingGridGeneratorTest {
    @Test
    void generate() throws Exception {
        final SudokuGrid sudokuGrid = new BacktrackingGridGenerator().generate();
        Assertions.assertNotNull(sudokuGrid);
        System.out.println(sudokuGrid);
    }
}