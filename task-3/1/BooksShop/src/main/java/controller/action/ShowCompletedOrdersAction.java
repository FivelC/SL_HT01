package controller.action;

import controller.ShopProvider;
import model.*;
import controller.ConsoleInput;
import view.IAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/** Показать выполненные заказы за период. */
public class ShowCompletedOrdersAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        SortOrder sort = input.choose("Сортировка:", List.of(SortOrder.values()));
        LocalDate from = input.readDate("С какой даты:");
        LocalDate to = input.readDate("По какую дату:");

        shop.getCompletedOrders(from,to,sort).forEach(System.out::println);
    }
}
