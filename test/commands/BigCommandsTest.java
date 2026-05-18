package commands;

import model.Warehouse;
import model.Workshop;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ScannerUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class BigCommandsTest {

    private Warehouse warehouse;
    private Workshop workshop;
    private final InputStream originalSystemIn = System.in;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
        workshop = new Workshop();
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ScannerUtil.reset();
    }

    @Test
    void testCreatePreciousStone() {
        String input = "1\nDiamond\n5\n1000\n10\n";
        provideInput(input);

        new CreateStoneCommand(warehouse).execute();

        assertFalse(warehouse.getAllStones().isEmpty(), "Камінь мав додатися на склад");
    }

    @Test
    void testCreateSemiPreciousStone() {
        String input = "2\nAmber\n10\n50\n5\nYellow\n";
        provideInput(input);

        new CreateStoneCommand(warehouse).execute();

        assertFalse(warehouse.getAllStones().isEmpty(), "Напівкоштовний камінь мав додатися");
    }

    @Test
    void testWorkshopCommandsWithoutGetters() {

        provideInput("GoldNecklace\n");
        new CreateNecklaceCommand(workshop).execute();

    }

    @Test
    void testViewCommands() {
        new ViewAllStonesCommand(warehouse).execute();

        new ViewAllNecklacesCommand(workshop).execute();
    }
}