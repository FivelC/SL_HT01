package controller.action;

import controller.ShopProvider;
import model.*;
import controller.ConsoleInput;
import view.IAction;

public class ShowDescriptionAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        Book book = input.choose("Описание какой книги получить?", shop.getBooks(SortBook.NONE));
        if(book == null) return;

        System.out.println(shop.getBookDescription(book));
    }
}
