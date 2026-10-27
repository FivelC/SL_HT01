package model;

import java.time.LocalDate;

public class Order {
    private final int id;
    private Book book;
    private User customer;
    private float cost;
    private LocalDate date;
    private BookRequest request;
    private OrderStatus status;

    public Order(int number, Book book, User customer, BookRequest request){
        this(number, book, customer, request, book.getCost(), null, OrderStatus.NEW);
    }

    public Order(int number, Book book, User customer, BookRequest request,
                 float cost, LocalDate date, OrderStatus status){
        if (number <= 0) throw new IllegalArgumentException("Id заказа должен быть больше 0: " + number);
        if (book == null) throw new IllegalArgumentException("У заказа должна быть книга");
        if (customer == null) throw new IllegalArgumentException("У заказа должен быть покупатель");
        if (status == null) throw new IllegalArgumentException("У заказа должен быть статус");
        if (!Float.isFinite(cost) || cost < 0) throw new IllegalArgumentException("Некорректная стоимость заказа: " + cost);
        if (request != null && request.getBook() != book)
            throw new IllegalArgumentException("Запрос #" + request.getId() + " относится к другой книге");
        if (status == OrderStatus.COMPLETED && date == null)
            throw new IllegalArgumentException("У выполненного заказа должна быть дата");
        if (status != OrderStatus.COMPLETED && date != null)
            throw new IllegalArgumentException("Дата выполнения есть только у выполненного заказа");
        if (date != null && date.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Дата выполнения в будущем: " + date);
        this.id = number;
        this.book = book;
        this.customer = customer;
        this.request = request;
        this.cost = cost;
        this.date = date;
        this.status = status;
    }

    public void update(Order other){
        if (other.id != this.id) throw new IllegalArgumentException("Нельзя обновить заказ чужими данными");
        this.book = other.book;
        this.customer = other.customer;
        this.request = other.request;
        this.cost = other.cost;
        this.date = other.date;
        this.status = other.status;
    }

    public void completed(LocalDate comp){
        status = OrderStatus.COMPLETED;
        date = comp;
    }

    public void canceled(){
        status = OrderStatus.CANCELED;
    }

    public boolean isWaitingForBook() {
        return request != null && request.isOpen();
    }

    public int getId() {
        return id;
    }

    public float getCost() {
        return cost;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDate() {
        return date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public User getCustomer() {
        return customer;
    }

    public BookRequest getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return "Заказ #" + id + " на " + book.getName() + " (" + cost + ", " + date + ") - " + status;
    }
}
