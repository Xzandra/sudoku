package org.xzandra.sudoku.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xzandra.sudoku.model.SudokuBoard;
import org.xzandra.sudoku.model.old.SudokuCellImpl;
import org.xzandra.sudoku.model.old.SudokuGrid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Loads Sudoku grid from the specified file.
 */
public class GridLoader {
    public static final String VALUE_SEPARATOR = ",";
    private static final Logger logger = LogManager.getLogger(GridLoader.class);

    /**
     * Load csv file with sudoku. The structure of the file - rows with sudoku cell values separated by comma.
     *
     * @param filePath - URI to the csv file with sudoku grid.
     * @return SudokuGrid object.
     */
    public SudokuGrid loadGrid(final URI filePath) {
        final SudokuGrid grid = new SudokuGrid();
        final int[] cellValues = loadCellsFromCsv(filePath);
        IntStream.range(0, cellValues.length)
                 .forEach(index -> grid.setCell(index, new SudokuCellImpl(index, cellValues[index])));
        grid.updateCellPossibilities();

        logger.debug("Loading of grid finished successfully, {}", grid);
        return grid;
    }

    /**
     * Load csv file with sudoku. The structure of the file - rows with sudoku cell values separated by comma.
     *
     * @param filePath - URI to the csv file with sudoku grid.
     * @return SudokuBoard object.
     */
    public SudokuBoard loadBoard(final URI filePath) {
        final int[] cellValues = loadCellsFromCsv(filePath);
        final SudokuBoard sudokuBoard = new SudokuBoard(cellValues);
        logger.debug("Loading of board finished successfully, {}", sudokuBoard);
        return sudokuBoard;
    }

    /**
     * Load csv file with sudoku. The structure of the file - rows with sudoku cell values separated by comma,
     * where 0 means empty cell.
     *
     * @param filePath - URI to the csv file with sudoku grid.
     * @return array of cell values.
     */
    public int[] loadCellsFromCsv(final URI filePath) {
        logger.debug("Loading of file {} started", filePath);
        Path path = Paths.get(filePath);

        try (BufferedReader reader = Files.newBufferedReader(
                path, Charset.forName("UTF-8"))) {
            final String combinedString = reader.lines()
                                                .collect(Collectors.joining(VALUE_SEPARATOR));
            logger.info("Loading of file {} finished successfully", filePath);
            return Arrays.stream(combinedString.split(VALUE_SEPARATOR))
                         .mapToInt(Integer::parseInt)
                         .toArray();
        } catch (IOException e) {
            logger.debug("Exception while reading file " + path.toString(), e);
            throw new UncheckedIOException(e);
        }
    }
}
