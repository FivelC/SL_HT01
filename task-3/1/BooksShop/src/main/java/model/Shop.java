package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Shop {
    private final int unsoldMonths;
    private final boolean autoCloseRequests;

    private final Storage storage;
    private final OrderProcessor orderProcessor;
    private final RequestProcessor requestProcessor;

    public Shop(Storage storage, OrderProcessor orderProcessor, RequestProcessor requestProcessor,int unsoldMonths,boolean autoCloseRequests){
        this.storage = storage;
        this.orderProcessor = orderProcessor;
        this.requestProcessor = requestProcessor;
        this.unsoldMonths = unsoldMonths;
        this.autoCloseRequests = autoCloseRequests;
    }

    public void removeBook(Book book){
        storage.removeBook(book);
    }

    public void storeBook(Book book){
        storeBook(book, LocalDate.now());
    }

    public void importBooks(List<Book> books){
        for(Book book : books){
            storage.importBook(book);
        }
    }

    public Book findBookById(int id){
        return storage.findById(id);
    }

    public BookRequest findRequestById(int id){
        return requestProcessor.findById(id);
    }

    public void importOrders(List<Order> orders){
        for (Order order : orders){
            orderProcessor.importOrder(order);
        }
    }

    public void importRequests(List<BookRequest> requests){
        for (BookRequest request : requests){
            requestProcessor.importRequest(request);
        }
    }

    public void storeBook(Book book, LocalDate arrivalDate){
        storage.storeBook(book, arrivalDate);
        if(autoCloseRequests) requestProcessor.closeRequests(book);

    }

//    public BookRequest createRequest(Book book, User customer){
//        if (storage.isAvailable(book)) throw new IllegalStateException(book.getName() + " есть в наличии");
//        return requestProcessor.createRequest(book, customer);
//    }
    public Order createOrder(Book book, User customer){
        BookRequest request = storage.isAvailable(book) ? null : requestProcessor.createRequest(book, customer);
        return orderProcessor.createOrder(book, request, customer);
    }

    public void cancelOrder(Order order){
        orderProcessor.cancelOrder(order);
    }

    public void closeRequest(BookRequest request){
        if (!storage.isAvailable(request.getBook()))
            throw new IllegalStateException("Нельзя закрыть запрос: книги «" + request.getBook().getName() + "» нет на складе");
        requestProcessor.closeRequest(request);
    }

    public void closeRequests(Book book){
        requestProcessor.closeRequests(book);
    }

    public void changeOrderStatus(Order order, OrderStatus status){
        orderProcessor.changeStatus(order, status);
    }

//    public void completeOrder(Order order, LocalDate date){
//        orderProcessor.changeStatus(order, OrderStatus.COMPLETED, date);
//    }

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
        LocalDate border = today.minusMonths(unsoldMonths);
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
        List<Order> compl = orderProcessor.getCompletedOrders(start, end, SortOrder.NONE);
        float res = (float)compl.stream().mapToDouble(Order::getCost).sum();
        return res;
    }
    public int getTotalOrders(LocalDate start, LocalDate end){
        List<Order> compl = orderProcessor.getCompletedOrders(start,end, SortOrder.NONE);
        return compl.size();
    }


    public List<BookRequest> getRequests() {
        return requestProcessor.getRequests();
    }

    public boolean isAutoCloseRequests() {
        return autoCloseRequests;
    }


    public List<RequestInfo> getRequests(SortRequest sort) {
        return requestProcessor.getRequests(sort);
    }

}
