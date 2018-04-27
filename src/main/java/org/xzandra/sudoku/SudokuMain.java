package org.xzandra.sudoku;

import org.xzandra.sudoku.generator.BacktrackingGridGenerator;

/**
 * Sudoku generator main class.
 */
public class SudokuMain {
    public static void main(String[] args) {
        final BacktrackingGridGenerator backtrackingGridGeneratorOld = new BacktrackingGridGenerator();
        try {
            backtrackingGridGeneratorOld.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
