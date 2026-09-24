package org.example;

public class Lenses implements IProductPart {
    private final double diopters;

    public Lenses(double diopters) {
        this.diopters = diopters;
    }

    @Override
    public String toString() {
        return "линзы (" + diopters + " D)";
    }
}
