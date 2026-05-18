package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class NecklaceTest {

    private Necklace necklace;
    private Stone cheap;
    private Stone expensive;

    @BeforeEach
    void setUp() {
        necklace = new Necklace("Test Necklace");

        cheap = new SemiPreciousStone("Cheap", 1.0, 100.0, 1);

        expensive = new PreciousStone("Expensive", 2.0, 1000.0, 10);
    }

    @Test
    void testAddAndRemove() {
        necklace.addStone(cheap);
        assertEquals(1, necklace.getAllStones().size());

        necklace.removeStone(1);
        assertTrue(necklace.getAllStones().isEmpty());
    }

    @Test
    void testCalculations() {
        necklace.addStone(cheap);
        necklace.addStone(expensive);

        assertEquals(3.0, necklace.calculateTotalWeight());
        assertEquals(2100.0, necklace.calculateTotalCost());
    }

    @Test
    void testSorting() {
        necklace.addStone(cheap);
        necklace.addStone(expensive);

        necklace.sortStonesByValue();

        assertEquals(expensive, necklace.getAllStones().get(0));
    }

    @Test
    void testFind() {
        necklace.addStone(cheap);
        necklace.addStone(expensive);

        List<Stone> found = necklace.findStonesByTransparency(9, 10);

        assertEquals(1, found.size());
        assertEquals(expensive, found.get(0));
    }
}