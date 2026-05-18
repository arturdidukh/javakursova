package commands;

import model.*;
import service.ScannerUtil;

public class SortStonesInNecklaceCommand implements Command {
    private Workshop workshop;

    public SortStonesInNecklaceCommand(Workshop workshop) {
        this.workshop = workshop;
    }

    @Override
    public void execute() {
        System.out.println("\n--- СОРТУВАННЯ НАМИСТА ---");
        new ViewAllNecklacesCommand(workshop).execute();
        if (workshop.getAllNecklaces().isEmpty()) return;

        int index = ScannerUtil.getInt("Оберіть намисто для сортування: ");
        try {
            Necklace n = workshop.getNecklace(index);
            n.sortStonesByValue();
            System.out.println("Камені в намисті відсортовано за цінністю.");

            int i = 1;
            for (Stone s : n.getAllStones()) {
                System.out.println(i++ + ". " + s.toString());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Помилка: Невірний номер.");
        }
    }
}
