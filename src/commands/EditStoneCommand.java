package commands;

import model.*;
import service.ScannerUtil;

public class EditStoneCommand implements Command {
    private Warehouse warehouse;

    public EditStoneCommand(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void execute() {
        System.out.println("\n--- РЕДАГУВАННЯ КАМЕНЯ ---");
        new ViewAllStonesCommand(warehouse).execute();

        if (warehouse.getAllStones().isEmpty()) return;

        int index = ScannerUtil.getInt("Оберіть номер каменя для редагування: ");
        try {
            Stone stone = warehouse.getStone(index);
            System.out.println("Редагуємо: " + stone.getName());
            System.out.println("1. Назву\n2. Вагу\n3. Ціну\n4. Прозорість");
            int field = ScannerUtil.getInt("Що змінити? > ");

            switch (field) {
                case 1 -> stone.setName(ScannerUtil.getString("Нова назва: "));
                case 2 -> stone.setCaratWeight(ScannerUtil.getDouble("Нова вага: "));
                case 3 -> stone.setPricePerCarat(ScannerUtil.getDouble("Нова ціна: "));
                case 4 -> stone.setTransparency(ScannerUtil.getInt("Нова прозорість: "));
                default -> System.out.println("Невірний вибір.");
            }
            System.out.println("Камінь оновлено.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Помилка: Невірний номер каменя.");
        }
    }
}