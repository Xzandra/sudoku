package org.xzandra.sudoku;

import org.xzandra.sudoku.generator.BacktrackingGridGenerator;

/**
 * Sudoku generator main class.
 */
public class SudokuMain {
    public static void main(String[] args) {
        final BacktrackingGridGenerator backtrackingGridGenerator = new BacktrackingGridGenerator();
        try {
            backtrackingGridGenerator.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
