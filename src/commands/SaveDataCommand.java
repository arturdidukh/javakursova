package commands;

import repository.StoneRepository;
import model.*;
import service.FileService;

public class SaveDataCommand implements Command {
    private Warehouse warehouse;
    private Workshop workshop;

    public SaveDataCommand(Warehouse warehouse, Workshop workshop) {
        this.warehouse = warehouse;
        this.workshop = workshop;
    }

    @Override
    public void execute() {
        StoneRepository repo = new StoneRepository();
        repo.deleteAll();

        for (Stone stone : warehouse.getAllStones()) {
            repo.save(stone);
        }
        System.out.println("Дані успішно синхронізовано з SQLite!");
    }
}