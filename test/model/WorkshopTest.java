package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WorkshopTest {

    @Test
    void testCreateAndGetNecklace() {

        Workshop workshop = new Workshop();

        workshop.createNecklace("Summer Vibes");
        workshop.createNecklace("Winter Chill");

        assertEquals(2, workshop.getAllNecklaces().size(), "Має бути 2 намиста");

        assertEquals("Summer Vibes", workshop.getNecklace(1).getName());
        assertEquals("Winter Chill", workshop.getNecklace(2).getName());
    }

    @Test
    void testGetNecklaceInvalidIndex() {
        Workshop workshop = new Workshop();
        workshop.createNecklace("Test");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            workshop.getNecklace(0); // Занадто малий (починаємо з 1)
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            workshop.getNecklace(2); // Занадто великий
        });
    }
}
