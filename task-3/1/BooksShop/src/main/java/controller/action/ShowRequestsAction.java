package controller.action;

import controller.ShopProvider;
import model.*;
import controller.ConsoleInput;
import view.IAction;

import java.util.List;

public class ShowRequestsAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        SortRequest sort = input.choose("В каком порядке выдать?", List.of(SortRequest.values()));
        shop.getRequests(sort).forEach(System.out::println);
    }
}
