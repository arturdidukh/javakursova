package commands;

import model.*;
import service.ScannerUtil;

public class ViewStonesInNecklaceCommand implements Command {
    private Workshop workshop;

    public ViewStonesInNecklaceCommand(Workshop workshop) {
        this.workshop = workshop;
    }

    @Override
    public void execute() {
        System.out.println("\n--- ОГЛЯД ВМІСТУ НАМИСТА ---");
        new ViewAllNecklacesCommand(workshop).execute();
        if (workshop.getAllNecklaces().isEmpty()) return;

        int index = ScannerUtil.getInt("Оберіть намисто: ");
        try {
            Necklace necklace = workshop.getNecklace(index);
            System.out.println("Вміст намиста '" + necklace.getName() + "':");

            int i = 1;
            for (Stone s : necklace.getAllStones()) {
                System.out.println(i++ + ". " + s.toString());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Помилка: Невірний номер.");
        }
    }
}