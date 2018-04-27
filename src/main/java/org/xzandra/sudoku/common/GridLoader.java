package org.xzandra.sudoku.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xzandra.sudoku.model.SudokuCell;
import org.xzandra.sudoku.model.SudokuGrid;

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
     * @return Grid object.
     */
    public SudokuGrid loadGridFromCsv(final URI filePath) {
        logger.debug("Loading of file {} started", filePath);
        Path path = Paths.get(filePath);

        final SudokuGrid grid = new SudokuGrid();

        try (BufferedReader reader = Files.newBufferedReader(
                path, Charset.forName("UTF-8"))) {
            final String combinedString = reader.lines()
                                                .collect(Collectors.joining(VALUE_SEPARATOR));
            final int[] cellValues = Arrays.stream(combinedString.split(VALUE_SEPARATOR))
                                           .mapToInt(Integer::parseInt)
                                           .toArray();
            IntStream.range(0, cellValues.length)
                     .forEach(index -> grid.setCell(index, new SudokuCell(cellValues[index], index)));
            grid.updateCellAvailables();
        } catch (IOException e) {
            logger.debug("Exception while reading file " + path.toString(), e);
            throw new UncheckedIOException(e);
        }

        logger.info("Loading of file {} finished successfully", filePath);
        logger.debug("Loading of file {} finished successfully, {}", filePath, grid);
        return grid;
    }
}
