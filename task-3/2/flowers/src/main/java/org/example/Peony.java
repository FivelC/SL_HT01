package org.example;

public class Peony extends Flower {
    private static final int BASE_BUD_COUNT = 1;
    private static final int COST_PER_EXTRA_BUD = 10;

    private final int budCount;

    public Peony(int cost, int budCount) {
        super("Пион", cost);
        this.budCount = budCount;
        int extraBuds = Math.max(0, budCount - BASE_BUD_COUNT);
        setCost(cost + extraBuds * COST_PER_EXTRA_BUD);
    }

    public int getBudCount() {
        return budCount;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", бутонов: %d", budCount);
    }
}