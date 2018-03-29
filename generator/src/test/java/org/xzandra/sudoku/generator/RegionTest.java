package org.xzandra.sudoku.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xzandra.sudoku.generator.model.Cell;
import org.xzandra.sudoku.generator.model.Region;

class RegionTest {
    @Test
    void isValidValue() {
        Region region = buildArea();

        Assertions.assertTrue(region.isValidValue(2));
    }

    @Test
    void isInvalidValue() {
        Region region = buildArea();

        Assertions.assertFalse(region.isValidValue(4));
    }

    private Region buildArea() {
        Region region = new Region();

        region.addCell(new Cell(0) {{
            setValue(7);
        }});
        region.addCell(new Cell(1) {{
            setValue(6);
        }});
        region.addCell(new Cell(2) {{
            setValue(5);
        }});
        region.addCell(new Cell(3) {{
            setValue(4);
        }});
        region.addCell(new Cell(4) {{
            setValue(8);
        }});
        region.addCell(new Cell(5) {{
            setValue(9);
        }});
        region.addCell(new Cell(6) {{
            setValue(1);
        }});
        region.addCell(new Cell(7) {{
            setValue(3);
        }});
        region.addCell(new Cell(8) {{
            setValue(0);
        }});
        return region;
    }
}