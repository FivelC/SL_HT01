package org.example;

public class TemplesLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println(" Дужки: делаем дужки с шарнирами");
        Temples temples = new Temples(140);
        System.out.println(" Дужки: готовы (" + temples + ") ");
        return temples;
    }
}
