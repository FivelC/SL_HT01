package controller.action;

import controller.ShopProvider;
import model.*;
import controller.ConsoleInput;
import view.IAction;
import view.OrderView;

public class ShowOrderInfoAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        Order order = input.choose("Информацию о каком заказе получить?", shop.getOrders(SortOrder.NONE));
        if(order == null) return;

        System.out.println(new OrderView().format(order));

    }
}
