package model;

import java.time.LocalDate;

public class Order {
    private final int id;
    private final Book book;
    private final User customer;

    private final float cost;
    private LocalDate date = null;
    private final BookRequest request;
    private OrderStatus status = OrderStatus.NEW;

    public Order(int number, Book book, User customer, BookRequest request){
        this.id = number;
        this.book = book;
        this.customer = customer;
        this.request = request;
        this.cost = book.getCost();
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

    @Override
    public String toString() {
        return "Заказ #" + id + " на " + book.getName() + " (" + cost + ", " + date + ") - " + status;
    }
}
