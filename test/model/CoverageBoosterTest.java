package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoverageBoosterTest {

    @Test
    void testAllModels() {
        PreciousStone p = new PreciousStone("Ruby", 2.0, 1000, 8);
        p.setTransparency(9);
        assertEquals(9, p.getTransparency());

        SemiPreciousStone s = new SemiPreciousStone("Topaz", 5.0, 100, 5);
        s.setDescription("Beautiful stone");
        assertEquals("Beautiful stone", s.getDescription());

        Necklace n = new Necklace("OldName");
        n.setName("NewName");
        assertEquals("NewName", n.getName());

        n.addStone(p);
        assertNotNull(n.getStones());
        assertEquals(1, n.getStones().size());
    }
}