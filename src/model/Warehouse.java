package model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public class Warehouse implements Serializable {
    private List<Stone> allStones;

    public Warehouse() {
        this.allStones = new ArrayList<>();
    }

    public void addStone(Stone stone) {
        this.allStones.add(stone);
    }

    public void removeStone(int index) {
        this.allStones.remove(index - 1);
    }

    public List<Stone> getAllStones() {
        return allStones;
    }

    public Stone getStone(int index) {
        return allStones.get(index - 1);
    }
}