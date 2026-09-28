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

    public List<Book> getBooks() {
        return storage.getBooks();
    }

    public List<Order> getOrders() {
        return orderProcessor.getOrders();
    }

    public List<BookRequest> getRequests() {
        return requestProcessor.getRequests();
    }
}
