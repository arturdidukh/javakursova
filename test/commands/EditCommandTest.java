package commands;

import model.PreciousStone;
import model.Warehouse;
import org.junit.jupiter.api.Test;
import service.ScannerUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class EditFullTest {

    @Test
    void testEditAllProperties() {
        Warehouse warehouse = new Warehouse();
        warehouse.addStone(new PreciousStone("Ruby", 1.0, 100, 10));


        String input =
                "1\n2\n5\n" +
                        "1\n3\n500\n" +
                        "1\n4\n5\n" +
                        "1\n99\n";

        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ScannerUtil.reset();

        try {
            Command cmd = new EditStoneCommand(warehouse);

            cmd.execute();
            cmd.execute();
            cmd.execute();
            cmd.execute();


            assertEquals(500.0, warehouse.getStone(1).getPricePerCarat());
            assertEquals(5, warehouse.getStone(1).getTransparency());

        } finally {
            System.setIn(originalIn);
        }
    }
}