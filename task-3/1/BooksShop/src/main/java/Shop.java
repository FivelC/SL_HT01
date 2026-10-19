import java.time.LocalDate;
import java.util.List;

public class Shop {
    private final Storage storage;
    private final OrderProcessor orderProcessor;
    private final RequestProcessor requestProcessor;

    public Shop(Storage storage, OrderProcessor orderProcessor, RequestProcessor requestProcessor){
        this.storage = storage;
        this.orderProcessor = orderProcessor;
        this.requestProcessor = requestProcessor;
    }

    public void removeBook(Book book){
        storage.removeBook(book);
    }

    public void storeBook(Book book){
        storage.storeBook(book);
        requestProcessor.closeRequests(book);
    }

    public BookRequest createRequest(Book book){
        if (storage.isAvailable(book)) throw new IllegalStateException(book.getName() + " есть в наличии");
        return requestProcessor.createRequest(book);
    }
    public Order createOrder(Book book){
        BookRequest request = storage.isAvailable(book) ? null : requestProcessor.createRequest(book);
        return orderProcessor.createOrder(book, request);
    }

    public void cancelOrder(Order order){
        orderProcessor.cancelOrder(order);
    }

    public void changeOrderStatus(Order order, OrderStatus status){
        orderProcessor.changeStatus(order, status);
    }

    public List<Book> getBooks(SortBook status) {
        return storage.getBooks(status);
    }

    public List<Order> getOrders(SortOrder status) {
        return orderProcessor.getOrders(status);
    }

    public List<Order> getCompletedOrders(LocalDate from, LocalDate to, SortOrder sort) {
        return orderProcessor.getCompletedOrders(from, to, sort);
    }
    public float getEarnedAmount(LocalDate start, LocalDate end){
        float res = 0;
        List<Order> compl = orderProcessor.getCompletedOrders(start, end, SortOrder.NONE);
        for(Order ord : compl){
            res += ord.getCost();
        }
        return res;
    }
    public int getTotalOrders
    public List<BookRequest> getRequests() {
        return requestProcessor.getRequests();
    }

    public List<RequestInfo> getRequests(SortRequest sort) {
        return requestProcessor.getRequests(sort);
    }
}
