package org.xzandra.sudoku.generator;

/**
 * Sudoku generator main class.
 */
public class SudokuMain {
    public static void main(String[] args) {
        final GridGenerator gridGenerator = new GridGenerator();
        try {
            gridGenerator.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
