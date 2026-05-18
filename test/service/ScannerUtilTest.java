package service;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class ScannerUtilFullTest {

    @Test
    void testScannerResilience() {
        String input = "bad_int\n10\nsome_text\nbad_double\n5,5\n5.5\n";

        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ScannerUtil.reset();

        try {
            int i = ScannerUtil.getInt("Введіть int: ");
            assertEquals(10, i);

            String s = ScannerUtil.getString("Введіть string: ");
            assertEquals("some_text", s);

            double d = ScannerUtil.getDouble("Введіть double: ");
            assertEquals(5.5, d, 0.01);

        } finally {
            System.setIn(originalIn);
        }
    }
}
