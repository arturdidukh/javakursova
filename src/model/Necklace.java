package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;

public class Necklace implements Serializable {
    private String name;
    private List<Stone> stones;

    public Necklace(String name) {
        this.name = name;
        this.stones = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Stone> getAllStones() { return stones; }

    public void addStone(Stone stone) {
        this.stones.add(stone);
    }

    public void removeStone(int index) {
        this.stones.remove(index - 1);
    }

    public double calculateTotalCost() {
        return stones.stream().mapToDouble(Stone::getTotalPrice).sum();
    }

    public double calculateTotalWeight() {
        return stones.stream().mapToDouble(Stone::getCaratWeight).sum();
    }

    public void sortStonesByValue() {
        stones.sort(Comparator.comparingDouble(Stone::getTotalPrice).reversed());
    }

    public List<Stone> findStonesByTransparency(int min, int max) {
        return stones.stream()
                .filter(s -> s.getTransparency() >= min && s.getTransparency() <= max)
                .collect(Collectors.toList());
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stone> getStones() {
        return stones;
    }

    @Override
    public String toString() {
        return String.format("%s (Каменів: %d, Загальна вага: %.2f carats, Загальна вартість: $%.2f)",
                name, stones.size(), calculateTotalWeight(), calculateTotalCost());
    }
}