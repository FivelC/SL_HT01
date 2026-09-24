package org.example;

public class Temples implements IProductPart {
    private final int length;

    public Temples(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "дужки (" + length + " мм)";
    }
}
