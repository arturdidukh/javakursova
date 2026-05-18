package commands;

import model.*;
import service.ScannerUtil;
import java.util.List;

public class FindStonesInNecklaceCommand implements Command {
    private Workshop workshop;

    public FindStonesInNecklaceCommand(Workshop workshop) {
        this.workshop = workshop;
    }

    @Override
    public void execute() {
        System.out.println("\n--- ПОШУК У НАМИСТІ ---");
        new ViewAllNecklacesCommand(workshop).execute();
        if (workshop.getAllNecklaces().isEmpty()) return;

        int index = ScannerUtil.getInt("Оберіть намисто: ");
        try {
            Necklace n = workshop.getNecklace(index);

            int min = ScannerUtil.getInt("Мін. прозорість (1-10): ");
            int max = ScannerUtil.getInt("Макс. прозорість (1-10): ");

            List<Stone> found = n.findStonesByTransparency(min, max);

            System.out.println("Знайдено каменів: " + found.size());
            for (Stone s : found) {
                System.out.println("- " + s.toString());
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Помилка: Невірний номер.");
        }
    }
}
