import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import service.ScannerUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

class MainTest {

    private final InputStream originalIn = System.in;

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);

        new File("warehouse.dat").delete();
        new File("workshop.dat").delete();
    }

    @Test
    void testMainFullScenario() {

        String input = "5\n3\n4\n1\n9\n2\n9\n9\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ScannerUtil.reset();

        try {
            Main.main(new String[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
