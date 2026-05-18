package service;

import org.junit.jupiter.api.Test;
import java.util.logging.Level;
import java.util.logging.LogRecord;

class EmailCriticalHandlerTest {

    @Test
    void testPublishCriticalError() {
        EmailCriticalHandler handler = new EmailCriticalHandler();

        LogRecord record = new LogRecord(Level.SEVERE, "Test Critical Error");

        handler.publish(record);

        LogRecord recordWithException = new LogRecord(Level.SEVERE, "Error with Exception");
        recordWithException.setThrown(new RuntimeException("Test Exception"));
        handler.publish(recordWithException);

        LogRecord infoRecord = new LogRecord(Level.INFO, "Just info");
        handler.publish(infoRecord);

        handler.flush();
        handler.close();
    }
}
