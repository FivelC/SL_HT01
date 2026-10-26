package controller.action;

import controller.ShopProvider;
import model.*;
import controller.ConsoleInput;
import view.IAction;
import view.OrderView;

public class CreateOrderAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        Book book = input.choose("Какую книгу хотите получить?", shop.getBooks(SortBook.NONE));
        if (book == null) {
            return;
        }
            try {
                String firstName = input.readLine("Ваше имя \n");
                String lastName = input.readLine("Ваша фамилия \n");
                Order order = shop.createOrder(book,new User(firstName,lastName));
                System.out.println("Заказ создан: \n" + new OrderView().format(order));

                if(order.isWaitingForBook()) System.out.println("Книги нет в наличии — создан запрос");

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
