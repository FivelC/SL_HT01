package org.example;

public class Glasses implements IProduct {
    private IProductPart frame;
    private IProductPart lenses;
    private IProductPart temples;

    @Override
    public void installFirstPart(IProductPart part) {
        frame = part;
        System.out.println("Установили " + part);
    }

    @Override
    public void installSecondPart(IProductPart part) {
        lenses = part;
        System.out.println("Установили " + part);
    }

    @Override
    public void installThirdPart(IProductPart part) {
        temples = part;
        System.out.println("Установили " + part);
    }

    @Override
    public String toString() {
        return "Очки: " + frame + ", " + lenses + ", " + temples;
    }
}
