package controller;

import controller.action.*;
import controller.factory.BookActionFactory;
import controller.factory.IActionFactory;
import controller.factory.OrderActionFactory;
import controller.factory.RequestActionFactory;
import view.Menu;
import view.MenuItem;

import java.util.List;

public class Builder {
    private Menu rootMenu;

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void buildMenu() {
        IActionFactory books = new BookActionFactory();
        IActionFactory orders = new OrderActionFactory();
        IActionFactory requests = new RequestActionFactory();

        rootMenu = new Menu();
        rootMenu.setName("Главное меню");

        Menu booksMenu = new Menu();
        booksMenu.setName("Книги");

        Menu ordersMenu = new Menu();
        ordersMenu.setName("Заказы");

        Menu requestsMenu = new Menu();
        requestsMenu.setName("Запросы");

        Menu CsvMenu = new Menu();
        CsvMenu.setName("Экспорт/Импорт");

        rootMenu.setMenuItems(List.of(
                new MenuItem("Книги", null, booksMenu),
                new MenuItem("Заказы", null, ordersMenu),
                new MenuItem("Запросы", null, requestsMenu),
                new MenuItem("Экспорт/Импорт", null, CsvMenu),
                new MenuItem("Выход", () -> System.exit(0), null)
        ));

        booksMenu.setMenuItems(List.of(
                new MenuItem("Показать книги", books.createShowAction(), booksMenu),
                new MenuItem("Поступление книги на склад", books.createAddAction(), booksMenu),
                new MenuItem("Списать книгу", books.createRemoveAction(), booksMenu),
                new MenuItem("Описание книги", new ShowDescriptionAction(), booksMenu),
                new MenuItem("Залежавшиеся книги", new ShowUnsoldBooksAction(), booksMenu),
                new MenuItem("Назад", null, rootMenu)
        ));

        ordersMenu.setMenuItems(List.of(
                new MenuItem("Показать заказы", orders.createShowAction(), ordersMenu),
                new MenuItem("Создать заказ", orders.createAddAction(), ordersMenu),
                new MenuItem("Выполнить заказ", new CompleteOrderAction(), ordersMenu),
                new MenuItem("Отменить заказ", orders.createRemoveAction(), ordersMenu),
                new MenuItem("Детали заказа", new ShowOrderInfoAction(), ordersMenu),
                new MenuItem("Выполненные заказы за период", new ShowCompletedOrdersAction(), ordersMenu),
                new MenuItem("Доход за период", new ShowEarnedAction(), ordersMenu),
                new MenuItem("Количество выполненных заказов за период", new ShowTotalOrdersAction(), ordersMenu),
                new MenuItem("Назад", null, rootMenu)
        ));

        requestsMenu.setMenuItems(List.of(
                new MenuItem("Показать запросы", requests.createShowAction(), requestsMenu),
                new MenuItem("Назад", null, rootMenu)
        ));

        CsvMenu.setMenuItems(List.of(
                new MenuItem("Экспорт Книг",new ExportBookAction(), CsvMenu),
                new MenuItem("Экспорт Заказов", new ExportOrderAction(), CsvMenu),
                new MenuItem("Экспорт Запросов", new ExportRequestAction(), CsvMenu),
                new MenuItem("Назад", null, rootMenu)
        ));
    }
}
