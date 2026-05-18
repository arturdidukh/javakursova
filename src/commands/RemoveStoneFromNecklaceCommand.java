package commands;

import model.Necklace;
import model.Stone;
import model.Workshop;
import service.ScannerUtil;
import java.util.List;

public class RemoveStoneFromNecklaceCommand implements Command {
    private Workshop workshop;

    public RemoveStoneFromNecklaceCommand(Workshop workshop) {
        this.workshop = workshop;
    }

    @Override
    public void execute() {
        System.out.println("\n--- ВИДАЛЕННЯ КАМЕНЯ З НАМИСТА ---");

        new ViewAllNecklacesCommand(workshop).execute();
        if (workshop.getAllNecklaces().isEmpty()) return;

        int nIndex = ScannerUtil.getInt("Оберіть номер намиста: ");

        try {
            Necklace necklace = workshop.getNecklace(nIndex);
            List<Stone> stonesInNecklace = necklace.getAllStones();

            if (stonesInNecklace.isEmpty()) {
                System.out.println("Це намисто порожнє.");
                return;
            }

            System.out.println("Камені в намисті '" + necklace.getName() + "':");
            int i = 1;
            for (Stone s : stonesInNecklace) {
                System.out.println(i++ + ". " + s.toString());
            }

            int sIndex = ScannerUtil.getInt("Оберіть номер каменя для видалення: ");

            if (sIndex < 1 || sIndex > stonesInNecklace.size()) {
                System.out.println("! Помилка: Невірний номер.");
                return;
            }


            Stone stoneToRemove = stonesInNecklace.get(sIndex - 1);

            necklace.removeStone(sIndex);

            stoneToRemove.setAvailable(true);

            System.out.println("Успіх: Камінь видалено з намиста і повернуто на склад (став доступним).");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("! Помилка: Невірний номер намиста.");
        }
    }
}
