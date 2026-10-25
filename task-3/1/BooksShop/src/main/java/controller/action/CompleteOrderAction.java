package controller.action;

import controller.ShopProvider;
import model.*;
import controller.ConsoleInput;
import view.IAction;

public class CompleteOrderAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        Order order = input.choose("Какой заказ выполнить?", shop.getOrders(SortOrder.NONE));
        if (order == null) {
            return;
        }
        try {
            shop.changeOrderStatus(order, OrderStatus.COMPLETED);
            System.out.println("Заказ выполнен: " + order);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
