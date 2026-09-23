package org.example;

public class Rose extends Flower {
    private static final int THORN_REMOVAL_COST = 20;

    private final boolean thornsRemoved;

    public Rose(int cost, boolean thornsRemoved) {
        super("Роза", cost);
        this.thornsRemoved = thornsRemoved;
        if (thornsRemoved) {
            setCost(cost + THORN_REMOVAL_COST);
        }
    }

    public boolean isThornsRemoved() {
        return thornsRemoved;
    }

    @Override
    public String toString() {
        return super.toString() + (thornsRemoved ? ", без шипов" : "");
    }
}