package service;

import model.PreciousStone;
import model.Stone;
import model.Warehouse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    private static final String TEST_FILE = "test_warehouse_coverage.dat";
    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
        warehouse.addStone(new PreciousStone("TestDiamond", 1.0, 5000, 10));
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSaveAndLoadWarehouse() {
        FileService.saveWarehouse(warehouse, TEST_FILE);

        File file = new File(TEST_FILE);
        assertTrue(file.exists(), "Файл повинен бути створений");
        assertTrue(file.length() > 0, "Файл не повинен бути порожнім");

        Warehouse loadedWarehouse = FileService.loadWarehouse(TEST_FILE);

        assertNotNull(loadedWarehouse);
        assertEquals(1, loadedWarehouse.getAllStones().size());
        assertEquals("TestDiamond", loadedWarehouse.getStone(1).getName());
    }

    @Test
    void testLoadFromNonExistentFile() {
        Warehouse empty = FileService.loadWarehouse("ghost_file.dat");
        assertNotNull(empty);
        assertTrue(empty.getAllStones().isEmpty());
    }
}