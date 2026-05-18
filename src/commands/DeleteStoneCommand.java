package commands;

import model.Warehouse;
import service.ScannerUtil;

public class DeleteStoneCommand implements Command {
    private Warehouse warehouse;

    public DeleteStoneCommand(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void execute() {
        System.out.println("\n--- ВИДАЛЕННЯ КАМЕНЯ ---");
        new ViewAllStonesCommand(warehouse).execute();

        if (warehouse.getAllStones().isEmpty()) return;

        int index = ScannerUtil.getInt("Введіть номер каменя для видалення: ");
        try {
            warehouse.removeStone(index);
            System.out.println("Камінь успішно видалено.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Помилка: Невірний номер.");
        }
    }
}
