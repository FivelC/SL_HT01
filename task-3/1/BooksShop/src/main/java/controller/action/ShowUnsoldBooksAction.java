package controller.action;

import controller.ShopProvider;
import model.*;
import controller.ConsoleInput;
import view.IAction;

import java.util.List;

public class ShowUnsoldBooksAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        SortUnsoldBook sort = input.choose("В каком порядке выдать?", List.of(SortUnsoldBook.values()));
        shop.getUnsoldBooks(sort).forEach(System.out::println);
    }
}
