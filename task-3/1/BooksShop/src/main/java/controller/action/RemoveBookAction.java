package controller.action;

import controller.ShopProvider;
import model.Book;
import model.Shop;
import model.SortBook;
import controller.ConsoleInput;
import view.IAction;

public class RemoveBookAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        Book book = input.choose("Какую книгу списать?", shop.getBooks(SortBook.NONE));
        if (book == null) {
            return;
        }
        try {
            shop.removeBook(book);
            System.out.println("Книга списана: " + book);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
