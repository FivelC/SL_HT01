package org.example;

public class Lily extends Flower {

    private final String color;
    private final boolean fragrant;


    public Lily(int cost, String color, boolean fragrant) {
        super("Лилия", cost);
        this.color = color;
        this.fragrant = fragrant;
    }

    public String getColor() {
        return color;
    }

    public boolean isFragrant() {
        return fragrant;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", цвет: %s", color);
    }
}