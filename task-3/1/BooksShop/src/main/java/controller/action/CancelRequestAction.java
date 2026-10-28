package controller.action;

import controller.ConsoleInput;
import controller.ShopProvider;
import model.Book;
import model.BookRequest;
import model.Shop;
import model.SortBook;
import view.IAction;

public class CancelRequestAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        if(shop.isAutoCloseRequests()){
            Book find = input.choose("Запросы на какую книгу закрыть?", shop.getBooks(SortBook.NONE));
            if (find == null) return;
            shop.closeRequests(find);
        } else {
            BookRequest request = input.choose("Какой закрыть запрос?",shop.getRequests().stream()
                                                                            .filter(BookRequest::isOpen).toList());
            if (request == null) return;
            try {
                shop.closeRequest(request);
            } catch (Exception e){
                System.out.println("Не получилось закрыть реквест: " + request.getId() + ". ошибка: " + e.getMessage());
            }

        }
    }
}
