package org.example;

public class Main {
    public static void main(String[] args) {
        IAssemblyLine line = new GlassesAssemblyLine(
                new FrameLineStep(),
                new LensesLineStep(),
                new TemplesLineStep());

        IProduct glasses = line.assembleProduct(new Glasses());
        System.out.println("Итог: " + glasses);
    }
}
