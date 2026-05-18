package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    @Test
    void testAddAndGetStone() {
        Warehouse w = new Warehouse();
        Stone s = new PreciousStone("A", 1, 1, 1);

        w.addStone(s);
        assertEquals(1, w.getAllStones().size());
        assertEquals(s, w.getStone(1));
    }

    @Test
    void testRemoveStone() {
        Warehouse w = new Warehouse();
        w.addStone(new PreciousStone("A", 1, 1, 1));

        w.removeStone(1);
        assertTrue(w.getAllStones().isEmpty());
    }
}