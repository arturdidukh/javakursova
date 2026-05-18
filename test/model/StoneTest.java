package model;

import repository.StoneRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StoneTest {
    @Test
    void testCalculations() {
        Stone stone = new PreciousStone("Ruby", 2.0, 1000.0, 5);
        assertEquals(2000.0, stone.getTotalPrice());
    }

    @Test
    void testRepositoryBasics() {
        StoneRepository repo = new StoneRepository();
        repo.findAll();
        repo.save(new PreciousStone("Тест", 1.0, 100.0, 5));
    }
}
