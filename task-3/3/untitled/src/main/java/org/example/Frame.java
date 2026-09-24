package org.example;

public class Frame implements IProductPart {
    private final String material;

    public Frame(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "корпус (" + material + ")";
    }
}
