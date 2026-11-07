package persistence;

import model.Book;
import model.BookRequest;
import model.Order;

import java.util.List;

public record ShopState(List<Book> books,
                        List<Order> orders,
                        List<BookRequest> requests,
                        boolean autoCloseRequests) {

}
