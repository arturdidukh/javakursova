package commands;

import model.*;
import java.util.List;

public class ViewAllStonesCommand implements Command {
    private Warehouse warehouse;

    public ViewAllStonesCommand(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void execute() {
        System.out.println("\n--- ВСІ КАМЕНІ НА СКЛАДІ ---");
        List<Stone> stones = warehouse.getAllStones();
        if (stones.isEmpty()) {
            System.out.println("Склад порожній.");
            return;
        }
        int i = 1;
        for (Stone s : stones) {
            System.out.println(i++ + ". " + s.toString());
        }
    }
}