package persistence;

import model.*;

import java.time.LocalDate;

public record OrderData(int id, int bookId, String firstName,
                        String lastName, Integer requestId, float cost,
                        LocalDate date, OrderStatus status) {
    public static OrderData from(Order order){
        return new OrderData(order.getId(), order.getBook().getId(),order.getCustomer().firstName(),
                order.getCustomer().lastName(),order.getRequest() == null ? null : order.getRequest().getId(),order.getCost(),
                order.getDate(),order.getStatus());
    }

    public Order toOrder(Shop shop){
        BookRequest request = null;
        if (requestId != null) {
            request = shop.findRequestById(requestId);
            if (request == null) throw new IllegalArgumentException("Запрос с id=" + requestId + " не найден");
        }
        if(shop.findBookById(bookId) == null)
            throw new IllegalArgumentException("У заказа должна быть книга");
        return new Order(id,shop.findBookById(bookId),new User(firstName,lastName),
                request,cost,date,status);
    }
}
