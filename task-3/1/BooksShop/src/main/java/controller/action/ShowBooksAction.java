package controller.action;

import controller.ShopProvider;
import model.Shop;
import model.SortBook;
import controller.ConsoleInput;
import view.IAction;

import java.util.List;

public class ShowBooksAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        SortBook sort = input.choose("Сортировка:", List.of(SortBook.values()));

        shop.getBooks(sort).forEach(System.out::println);
    }
}
