package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Shop {
    private static final int UNSOLD_MONTHS = 6;

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
        storeBook(book, LocalDate.now());
    }

    public void storeBook(Book book, LocalDate arrivalDate){
        storage.storeBook(book, arrivalDate);
        requestProcessor.closeRequests(book);
    }

    public BookRequest createRequest(Book book){
        if (storage.isAvailable(book)) throw new IllegalStateException(book.getName() + " есть в наличии");
        return requestProcessor.createRequest(book);
    }
    public Order createOrder(Book book, User customer){
        BookRequest request = storage.isAvailable(book) ? null : requestProcessor.createRequest(book);
        return orderProcessor.createOrder(book, request, customer);
    }

    public void cancelOrder(Order order){
        orderProcessor.cancelOrder(order);
    }

    public void changeOrderStatus(Order order, OrderStatus status){
        orderProcessor.changeStatus(order, status);
    }

    public void completeOrder(Order order, LocalDate date){
        orderProcessor.changeStatus(order, OrderStatus.COMPLETED, date);
    }

    public List<Book> getBooks(SortBook status) {
        return storage.getBooks(status);
    }

    public String getBookDescription(Book book){
        return storage.getDescription(book);
    }

    public List<Book> getUnsoldBooks(SortUnsoldBook sort){
        return getUnsoldBooks(sort, LocalDate.now());
    }

    public List<Book> getUnsoldBooks(SortUnsoldBook sort, LocalDate today){
        LocalDate border = today.minusMonths(UNSOLD_MONTHS);
        List<Book> res = new ArrayList<>();
        for (Book book : storage.getBooks(SortBook.NONE)){
            if (book.getStatus() != BookStatus.AVAILABLE) continue;
            if (book.getArrivalDate().isAfter(border)) continue;
            LocalDate lastSale = orderProcessor.getLastSaleDate(book);
            if (lastSale == null || lastSale.isBefore(border)) res.add(book);
        }
        Comparator<Book> comparator = switch (sort){
            case SortUnsoldBook.DATE -> Comparator.comparing(Book::getArrivalDate);
            case SortUnsoldBook.COST -> Comparator.comparing(Book::getCost);
        };
        res.sort(comparator);
        return res;
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
    public int getTotalOrders(LocalDate start, LocalDate end){
        int res = 0;
        List<Order> compl = orderProcessor.getCompletedOrders(start,end, SortOrder.NONE);
        for(Order ord : compl){
            res++;
        }
        return res;
    }

    public String getOrderInfo(Order order){
        Book book = order.getBook();
        return "Заказ: " + order.getNumber() + '\n' +
                "Книга: " + book.getName() + '\n' +
                "Статус: " + order.getStatus() + '\n' +
                "Дата выполнения: " + (order.getDate() == null ? "не выполнен" : order.getDate()) + '\n' +
                "К оплате: " + order.getCost() + '\n' +
                "Заказчик: " + order.getCustomer();
    }

    public List<BookRequest> getRequests() {
        return requestProcessor.getRequests();
    }

    public List<RequestInfo> getRequests(SortRequest sort) {
        return requestProcessor.getRequests(sort);
    }
}
