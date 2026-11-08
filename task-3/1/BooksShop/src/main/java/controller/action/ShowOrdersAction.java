package controller.action;

import controller.ShopProvider;
import model.*;
import controller.ConsoleInput;
import view.IAction;

import java.io.IOException;
import java.util.List;

public class ShowOrdersAction implements IAction {
    @Override
    public void execute() throws IOException {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        SortOrder sort = input.choose("В каком порядке выдать?", List.of(SortOrder.values()));
        shop.getOrders(sort).forEach(System.out::println);

    }
}
