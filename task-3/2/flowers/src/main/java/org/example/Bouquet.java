package org.example;

import java.util.ArrayList;
import java.util.List;

public class Bouquet {
    private static final int WRAPPING_COST = 150;

    private final List<Flower> flowers = new ArrayList<>();
    private boolean wrapped;

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public void setWrapped(boolean wrapped) {
        this.wrapped = wrapped;
    }

    public int getFlowerCount() {
        return flowers.size();
    }

    public int getTotalCost() {
        int total = 0;
        for (Flower flower : flowers) {
            total += flower.getCost();
        }
        if (wrapped) {
            total += WRAPPING_COST;
        }
        return total;
    }

    public List<Flower> getFlowers() {
        return new ArrayList<>(flowers);
    }

    @Override
    public String toString() {
        StringBuilder total = new StringBuilder("Букет из ").append(flowers.size()).append(" цветов:\n");
        for (Flower flower : flowers) {
            total.append(" - ").append(flower).append("\n");
        }
        if (wrapped) {
            total.append(" + упаковка - ").append(WRAPPING_COST).append(" руб\n");
        }
        total.append("Итого: ").append(getTotalCost()).append(" руб.");
        return total.toString();
    }
}