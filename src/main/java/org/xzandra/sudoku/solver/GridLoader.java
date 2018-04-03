package org.xzandra.sudoku.solver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xzandra.sudoku.common.CellBuilder;
import org.xzandra.sudoku.common.CellValuesUtils;
import org.xzandra.sudoku.model.Cell;
import org.xzandra.sudoku.model.Grid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
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
    // TODO: 03.04.2018 see if more error handling is required
    public Grid loadCsv(final URI filePath) {
        logger.debug("Loading of file {} started", filePath);
        Cell[] cells;
        Path path = Paths.get(filePath);

        try (BufferedReader reader = Files.newBufferedReader(
                path, Charset.forName("UTF-8"))) {
            final String combinedString = reader.lines()
                                                .collect(Collectors.joining(VALUE_SEPARATOR));
            final int[] cellValues = Arrays.stream(combinedString.split(VALUE_SEPARATOR))
                                           .mapToInt(Integer::parseInt)
                                           .toArray();
            cells = IntStream.range(0, cellValues.length)
                             .mapToObj(index -> new CellBuilder(index).value(cellValues[index])
                                                                      .build())
                             .toArray(size -> new Cell[cellValues.length]);
        } catch (IOException e) {
            logger.debug("Exception while reading file " + path.toString(), e);
            throw new UncheckedIOException(e);
        }

        final List<Integer>[] validValues = new CellValuesUtils().initializeValidValuesForCells(cells);
        logger.info("Loading of file {} finished successfully", filePath);
        logger.debug("Loading of file {} finished successfully, {}", filePath, new Grid(cells, validValues));
        return new Grid(cells, validValues);
    }
}
