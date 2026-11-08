package controller.action;

import controller.ShopProvider;
import model.*;
import controller.ConsoleInput;
import view.IAction;

import java.io.IOException;

public class StoreBookAction implements IAction {
    @Override
    public void execute() throws IOException {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        Book book = input.choose("Какую книгу добавить?", shop.getBooks(SortBook.NONE));
        if (book == null) {
            return;
        }
        try {
            shop.storeBook(book);
            System.out.println("Книга добавлена: " + book);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
