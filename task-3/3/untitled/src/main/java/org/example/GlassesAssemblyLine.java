package org.example;

public class GlassesAssemblyLine implements IAssemblyLine {
    private final ILineStep frameStep;
    private final ILineStep lensesStep;
    private final ILineStep templesStep;

    public GlassesAssemblyLine(ILineStep frameStep, ILineStep lensesStep, ILineStep templesStep) {
        this.frameStep = frameStep;
        this.lensesStep = lensesStep;
        this.templesStep = templesStep;
        System.out.println("Линия сборки для очков создана");
    }

    @Override
    public IProduct assembleProduct(IProduct product) {
        System.out.println("Сборка началась");

        System.out.println("Этап 1 - корпус");
        product.installFirstPart(frameStep.buildProductPart());

        System.out.println("Этап 2 - линзы");
        product.installSecondPart(lensesStep.buildProductPart());

        System.out.println("Этап 3 - дужки");
        product.installThirdPart(templesStep.buildProductPart());

        System.out.println("Сборка закончена");
        return product;
    }
}
