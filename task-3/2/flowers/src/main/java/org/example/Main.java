package org.example;

public class Main {
    public static void main(String[] args) {
        Bouquet bouquet = new Bouquet();

        bouquet.addFlower(new Rose(120, true));
        bouquet.addFlower(new Rose(120, true));
        bouquet.addFlower(new Rose(120, false));
        bouquet.addFlower(new Sunflower(150, 15));
        bouquet.addFlower(new Lily(130, "белый", true));
        bouquet.addFlower(new Lily(130, "розовый", false));
        bouquet.addFlower(new Peony(140, 3));

        bouquet.setWrapped(true);
        System.out.println(bouquet);
    }
}