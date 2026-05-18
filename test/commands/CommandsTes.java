package commands;

import model.Warehouse;
import model.Workshop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class CommandsTest {

    private Warehouse warehouse;
    private Workshop workshop;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
        workshop = new Workshop();
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void testCreateStoneCommand() {
        String input = "1\nRuby\n2,5\n1000\n8\n";

        provideInput(input);

    }


    @Test
    void testCreateStoneManual() {
    }
}
