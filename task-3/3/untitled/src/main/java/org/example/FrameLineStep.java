package org.example;

public class FrameLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println(" Корпус: отливаем из ацетата");
        Frame frame = new Frame("ацетат");
        System.out.println(" Корпус: готов, " + frame);
        return frame;
    }
}
