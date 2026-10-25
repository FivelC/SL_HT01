package controller.action;

import controller.ShopProvider;
import model.*;
import controller.ConsoleInput;
import view.IAction;

/** Поступление книги на склад. */
public class StoreBookAction implements IAction {
    @Override
    public void execute() {
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
