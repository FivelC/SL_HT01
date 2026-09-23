package org.example;

public abstract class Flower {
    private final String name;
    private int cost;

    public Flower(String name, int cost) {
        this.name = name;
        setCost(cost);
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("!!сost cannot be negative");
        }
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%s — %d руб.", name, cost);
    }
}