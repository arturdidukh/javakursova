package model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Workshop implements Serializable {
    private List<Necklace> allNecklaces;

    public Workshop() {
        this.allNecklaces = new ArrayList<>();
    }

    public void createNecklace(String name) {
        this.allNecklaces.add(new Necklace(name));
    }

    public List<Necklace> getAllNecklaces() {
        return allNecklaces;
    }

    public Necklace getNecklace(int index) {

        if (index < 1 || index > allNecklaces.size()) {
            throw new IndexOutOfBoundsException("Невірний номер намиста.");
        }
        return allNecklaces.get(index - 1);
    }
}
