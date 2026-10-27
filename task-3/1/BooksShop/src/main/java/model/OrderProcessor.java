package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderProcessor {
    private List<Order> orders = new ArrayList<>();
    private int lastOrderNumber = 0;

    public Order createOrder(Book book, BookRequest request, User customer){
        Order order = new Order(++lastOrderNumber, book, customer ,request);
        orders.add(order);
        return order;
    }

    public Order findById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) return order;
        }
        return null;
    }

    public void importOrder(Order order) {
        Order existing = findById(order.getId());
        if (existing != null) {
            existing.update(order);
        } else {
            orders.add(order);
        }

        lastOrderNumber = Math.max(lastOrderNumber, order.getId());
    }

    public void changeStatus(Order order, OrderStatus status){
        changeStatus(order, status, LocalDate.now());
    }

    public void changeStatus(Order order, OrderStatus status, LocalDate date){
        if (!orders.contains(order)) throw new IllegalArgumentException("Заказ не найден");
        if (order.getStatus() != OrderStatus.NEW) throw new IllegalStateException(order + ": заказ уже закрыт");

        switch (status){
            case OrderStatus.COMPLETED -> {
                if (order.isWaitingForBook()) throw new IllegalStateException(order + ": книга ещё не поступила");
                order.completed(date);
            }
            case OrderStatus.CANCELED -> order.canceled();
            case OrderStatus.NEW -> {}
        }
    }

    public void cancelOrder(Order order){
        changeStatus(order, OrderStatus.CANCELED);
    }

    public LocalDate getLastSaleDate(Book book){
        LocalDate last = null;
        for (Order order : orders){
            if (order.getBook() == book && order.getStatus() == OrderStatus.COMPLETED
                    && (last == null || order.getDate().isAfter(last))) {
                last = order.getDate();
            }
        }
        return last;
    }

    public List<Order> getOrders(SortOrder status) {
        Comparator<Order> comparator = switch (status){
            case SortOrder.DATE -> Comparator.comparing(Order::getDate, Comparator.nullsLast(Comparator.naturalOrder()));
            case SortOrder.COST -> Comparator.comparing(Order::getCost);
            case SortOrder.STATUS -> Comparator.comparing(Order::getStatus);
            case SortOrder.NONE -> null;
        };
        List<Order> res = new ArrayList<>(orders);
        if (comparator != null) res.sort(comparator);
        return res;
    }

    public List<Order> getCompletedOrders(LocalDate from, LocalDate to, SortOrder sort) {
        Comparator<Order> comparator = switch (sort){
            case SortOrder.DATE -> Comparator.comparing(Order::getDate);
            case SortOrder.COST -> Comparator.comparing(Order::getCost);
            case SortOrder.STATUS, SortOrder.NONE -> null;
        };
        List<Order> res = new ArrayList<>();
        for (Order order : orders){
            if (order.getStatus() == OrderStatus.COMPLETED
                    && !order.getDate().isBefore(from)
                    && !order.getDate().isAfter(to)) {
                res.add(order);
            }
        }
        if (comparator != null) res.sort(comparator);
        return res;
    }
}
