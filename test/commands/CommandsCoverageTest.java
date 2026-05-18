package commands;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class CommandsCoverageTest {

    private Warehouse warehouse;
    private Workshop workshop;
    private final InputStream originalSystemIn = System.in;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
        workshop = new Workshop();

        warehouse.addStone(new PreciousStone("Ruby", 1.0, 1000, 10)); // ID 1
        warehouse.addStone(new SemiPreciousStone("Amber", 5.0, 50, 5)); // ID 2

        workshop.createNecklace("TestNecklace"); // ID 1
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
        new File("warehouse.dat").delete();
        new File("workshop.dat").delete();
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        service.ScannerUtil.reset();
    }

    @Test
    void testEditStoneCommand() {
        String input = "1\n1\nDiamond\n9\n";
        provideInput(input);

        new EditStoneCommand(warehouse).execute();

        assertEquals("Diamond", warehouse.getStone(1).getName());
    }




    @Test
    void testDeleteStoneCommand() {
        provideInput("2\n");
        new DeleteStoneCommand(warehouse).execute();
        assertEquals(1, warehouse.getAllStones().size());
    }

    @Test
    void testAddStoneToNecklaceCommand() {
        provideInput("1\n1\n");
        new AddStoneToNecklaceCommand(workshop, warehouse).execute();
        assertFalse(workshop.getNecklace(1).getStones().isEmpty());
    }

    @Test
    void testNecklaceOperations() {
        Necklace n = workshop.getNecklace(1);
        n.addStone(warehouse.getStone(1));

        provideInput("1\n");
        new ViewStonesInNecklaceCommand(workshop).execute();

        provideInput("1\n");
        new CalculateNecklaceCommand(workshop).execute();

        provideInput("1\n");
        new SortStonesInNecklaceCommand(workshop).execute();
    }

    @Test
    void testRemoveStoneFromNecklaceCommand() {
        Stone stone = warehouse.getStone(1);
        Necklace necklace = workshop.getNecklace(1);
        necklace.addStone(stone);

        provideInput("1\n1\n");
        new RemoveStoneFromNecklaceCommand(workshop).execute();

        assertTrue(necklace.getStones().isEmpty());
    }

    @Test
    void testFindStonesInNecklaceCommand() {
        workshop.getNecklace(1).addStone(warehouse.getStone(1));
        provideInput("1\n1\n10\n");
        new FindStonesInNecklaceCommand(workshop).execute();
    }

    @Test
    void testSaveAndLoadCommands() {
        new SaveDataCommand(warehouse, workshop).execute();
        File wFile = new File("warehouse.dat");
        assertTrue(wFile.exists());

        new LoadDataCommand(new Warehouse(), new Workshop()).execute();
    }

    @Test
    void testViewInfoCommands() {
        new ViewAllStonesCommand(warehouse).execute();
        new ViewAllNecklacesCommand(workshop).execute();
        new ShowHelpCommand().execute();
    }
}