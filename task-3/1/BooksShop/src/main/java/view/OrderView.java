package view;

import model.Book;
import model.Order;

public class OrderView {
    public String format(Order order) {
        Book book = order.getBook();
        return "Заказ: " + order.getId() + '\n' +
                "Книга: " + book.getName() + '\n' +
                "Статус: " + order.getStatus() + '\n' +
                "Дата выполнения: " + (order.getDate() == null ? "не выполнен" : order.getDate()) + '\n' +
                "К оплате: " + order.getCost() + '\n' +
                "Заказчик: " + order.getCustomer();
    }
}
