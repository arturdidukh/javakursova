package commands;

import model.*;
import service.ScannerUtil;
import service.LoggerService;
import java.util.logging.Logger;

public class CreateStoneCommand implements Command {
    private Warehouse warehouse;
    private final Logger logger = LoggerService.getLogger();

    public CreateStoneCommand(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void execute() {
        System.out.println("\n--- СТВОРЕННЯ НОВОГО КАМЕНЯ ---");

        String type = ScannerUtil.getString("Введіть тип (1 - Дорогоцінний, 2 - Напівкоштовний): ");
        String name = ScannerUtil.getString("Назва: ");
        double weight = ScannerUtil.getDouble("Вага (карати): ");
        double price = ScannerUtil.getDouble("Ціна за карат ($): ");
        int transparency = ScannerUtil.getInt("Прозорість (1-10): ");

        Stone newStone;
        if (type.equals("1")) {
            newStone = new PreciousStone(name, weight, price, transparency);
        } else {
            newStone = new SemiPreciousStone(name, weight, price, transparency);
        }

        warehouse.addStone(newStone);
        System.out.println("Успіх: Камінь додано на склад.");


        logger.info("User created stone: " + newStone.toString());
    }
}