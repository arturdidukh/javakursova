package commands;

import model.Workshop;
import service.ScannerUtil;
import service.LoggerService;
import java.util.logging.Logger;

public class CreateNecklaceCommand implements Command {
    private Workshop workshop;
    private final Logger logger = LoggerService.getLogger();

    public CreateNecklaceCommand(Workshop workshop) {
        this.workshop = workshop;
    }

    @Override
    public void execute() {
        System.out.println("\n--- СТВОРЕННЯ НАМИСТА ---");
        String name = ScannerUtil.getString("Введіть назву нового намиста: ");

        if (name.isEmpty()) {
            System.out.println("Назва не може бути порожньою.");
            return;
        }

        workshop.createNecklace(name);
        System.out.println("Намисто '" + name + "' створено.");

        logger.info("User created necklace: " + name);
    }
}