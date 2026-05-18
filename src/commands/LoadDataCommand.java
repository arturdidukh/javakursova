package commands;

import repository.StoneRepository;
import model.*;
import java.util.List;
import service.FileService;

public class LoadDataCommand implements Command {
    private Warehouse warehouse;
    private Workshop workshop;

    public LoadDataCommand(Warehouse warehouse, Workshop workshop) {
        this.warehouse = warehouse;
        this.workshop = workshop;
    }

    @Override
    public void execute() {
        System.out.println("--- Завантаження даних з SQLite ---");

        StoneRepository repo = new StoneRepository();
        List<Stone> loadedStones = repo.findAll();

        if (loadedStones.isEmpty()) {
            System.out.println("[!] База даних порожня. Нічого завантажувати.");
        } else {
            warehouse.getAllStones().clear();

            for (Stone s : loadedStones) {
                warehouse.addStone(s);
            }
            System.out.println("Успіх: Завантажено каменів з БД: " + loadedStones.size());
        }
    }
}