package commands;

import model.*;
import service.ScannerUtil;

public class AddStoneToNecklaceCommand implements Command {
    private Workshop workshop;
    private Warehouse warehouse;

    public AddStoneToNecklaceCommand(Workshop workshop, Warehouse warehouse) {
        this.workshop = workshop;
        this.warehouse = warehouse;
    }

    @Override
    public void execute() {
        System.out.println("\n--- ДОДАВАННЯ КАМЕНЯ В НАМИСТО ---");

        new ViewAllNecklacesCommand(workshop).execute();

        if (workshop.getAllNecklaces().isEmpty()) {
            System.out.println("Спочатку створіть намисто.");
            return;
        }

        int nIndex = ScannerUtil.getInt("Оберіть номер намиста: ");

        new ViewAllStonesCommand(warehouse).execute();

        if (warehouse.getAllStones().isEmpty()) {
            System.out.println("Склад порожній.");
            return;
        }

        int sIndex = ScannerUtil.getInt("Оберіть номер каменя зі складу: ");

        try {
            Necklace necklace = workshop.getNecklace(nIndex);
            Stone stone = warehouse.getStone(sIndex);

            if (necklace.getAllStones().contains(stone)) {
                System.out.println("! Помилка: Цей камінь ВЖЕ додано у це намисто.");
                return;
            }

            if (!stone.isAvailable()) {
                System.out.println("! Помилка: Цей камінь вже використано в ІНШОМУ намисті.");
                return;
            }

            necklace.addStone(stone);

            stone.setAvailable(false);

            System.out.println(String.format("Успіх: Камінь '%s' додано в намисто '%s'!",
                    stone.getName(), necklace.getName()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("! Помилка: Невірний номер намиста або каменя.");
        }
    }
}