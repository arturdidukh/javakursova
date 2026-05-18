package commands;

import model.*;
import service.ScannerUtil;

public class CalculateNecklaceCommand implements Command {
    private Workshop workshop;

    public CalculateNecklaceCommand(Workshop workshop) {
        this.workshop = workshop;
    }

    @Override
    public void execute() {
        System.out.println("\n--- РОЗРАХУНОК НАМИСТА ---");
        new ViewAllNecklacesCommand(workshop).execute();
        if (workshop.getAllNecklaces().isEmpty()) return;

        int index = ScannerUtil.getInt("Оберіть намисто: ");
        try {
            Necklace n = workshop.getNecklace(index);
            System.out.printf("Намисто: %s%n", n.getName());
            System.out.printf("Загальна вага: %.2f карати%n", n.calculateTotalWeight());
            System.out.printf("Загальна вартість: $%.2f%n", n.calculateTotalCost());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Помилка: Невірний номер.");
        }
    }
}