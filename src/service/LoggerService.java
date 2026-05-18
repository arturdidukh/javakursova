package service;

import java.io.IOException;
import java.util.logging.*;

public class LoggerService {

    private static final Logger logger = Logger.getLogger("JewelryApp");

    public static void setup() {
        try {
            LogManager.getLogManager().reset();
            logger.setLevel(Level.ALL);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.WARNING);
            logger.addHandler(consoleHandler);

            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);

            EmailCriticalHandler emailHandler = new EmailCriticalHandler();
            emailHandler.setLevel(Level.SEVERE);
            logger.addHandler(emailHandler);

        } catch (IOException e) {
            System.err.println("Не вдалося налаштувати логер: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}