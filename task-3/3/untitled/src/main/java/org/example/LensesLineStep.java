package org.example;

public class LensesLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println(" Линзы: шлифовка и полировка");
        Lenses lenses = new Lenses(-1.5);
        System.out.println(" Линзы: готовы, " + lenses);
        return lenses;
    }
}
