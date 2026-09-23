package org.example;

public class Sunflower extends Flower {
    private final int petalWidth;

    public Sunflower(int cost, int petalWidth) {
        super("Подсолнух", cost);
        this.petalWidth = petalWidth;
    }

    public int getPetalWidth() {
        return petalWidth;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", ширина лепестка %d см", petalWidth);
    }
}