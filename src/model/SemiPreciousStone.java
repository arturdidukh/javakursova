package model;

public class SemiPreciousStone extends Stone {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public SemiPreciousStone(String name, double caratWeight, double pricePerCarat, int transparency) {
        super(name, caratWeight, pricePerCarat, transparency);
    }
}
