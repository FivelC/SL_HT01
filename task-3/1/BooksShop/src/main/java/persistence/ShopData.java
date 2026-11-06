package persistence;

import model.Shop;
import model.SortBook;
import model.SortOrder;
import model.SortRequest;
import persistence.BookData;
import persistence.OrderData;
import persistence.RequestData;

import java.util.List;

public record ShopData(List<BookData> books, List<RequestData> requests, List<OrderData> orders) {

    public static ShopData from(Shop shop) {
        return new ShopData(
                shop.getBooks(SortBook.NONE).stream().map(BookData::from).toList(),
                shop.getRequests().stream().map(RequestData::from).toList(),
                shop.getOrders(SortOrder.NONE).stream().map(OrderData::from).toList()
        );
    }

    public void loadInto(Shop shop) {
        shop.importBooks(books.stream().map(BookData::toBook).toList());
        shop.importRequests(requests.stream().map(r -> r.toRequest(shop)).toList());
        shop.importOrders(orders.stream().map(o -> o.toOrder(shop)).toList());
    }
}