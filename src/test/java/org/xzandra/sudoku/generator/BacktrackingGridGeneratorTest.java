package org.xzandra.sudoku.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BacktrackingGridGeneratorTest {
    @Test
    void generate() throws Exception {
        Assertions.assertNotNull(new BacktrackingGridGenerator().generate());
    }
}