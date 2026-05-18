package model;

import java.io.Serializable;

public abstract class Stone implements Serializable {

    private String name;
    private double caratWeight;
    private double pricePerCarat;
    private int transparency;
    private boolean isAvailable = true;

    public Stone(String name, double caratWeight, double pricePerCarat, int transparency) {
        this.name = name;
        this.caratWeight = caratWeight;
        this.pricePerCarat = pricePerCarat;
        this.transparency = transparency;
    }

    public String getName() { return name; }
    public double getCaratWeight() { return caratWeight; }
    public double getPricePerCarat() { return pricePerCarat; }
    public int getTransparency() { return transparency; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) { isAvailable = available; }
    public void setName(String name) { this.name = name; }
    public void setCaratWeight(double caratWeight) { this.caratWeight = caratWeight; }
    public void setPricePerCarat(double pricePerCarat) { this.pricePerCarat = pricePerCarat; }
    public void setTransparency(int transparency) { this.transparency = transparency; }

    public double getTotalPrice() {
        return caratWeight * pricePerCarat;
    }

    @Override
    public String toString() {

        String status = isAvailable ? "[Вільний]" : "[Використаний]";

        return String.format("%s %s (%.2f carats, Clarity: %d, Value: $%.2f)",
                status,
                name,
                caratWeight,
                transparency,
                getTotalPrice());
    }
}
