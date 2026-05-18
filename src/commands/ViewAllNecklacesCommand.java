package commands;

import model.*;
import java.util.List;

public class ViewAllNecklacesCommand implements Command {
    private Workshop workshop;

    public ViewAllNecklacesCommand(Workshop workshop) {
        this.workshop = workshop;
    }

    @Override
    public void execute() {
        System.out.println("\n--- СПИСОК НАМИСТ ---");
        List<Necklace> list = workshop.getAllNecklaces();
        if (list.isEmpty()) {
            System.out.println("Майстерня порожня.");
            return;
        }
        int i = 1;
        for (Necklace n : list) {
            System.out.println(i++ + ". " + n.toString());
        }
    }
}
