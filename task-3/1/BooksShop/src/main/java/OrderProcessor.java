import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderProcessor {
    private List<Order> orders = new ArrayList<>();
    private int lastOrderNumber = 0;

    public Order createOrder(Book book, BookRequest request){
        Order order = new Order(++lastOrderNumber, book, request);
        orders.add(order);
        return order;
    }

    public void changeStatus(Order order, OrderStatus status){
        if (!orders.contains(order)) throw new IllegalArgumentException("Заказ не найден");
        if (order.getStatus() != OrderStatus.NEW) throw new IllegalStateException(order + ": заказ уже закрыт");

        switch (status){
            case COMPLETED -> {
                if (order.isWaitingForBook()) throw new IllegalStateException(order + ": книга ещё не поступила");
                order.completed();
            }
            case CANCELED -> order.canceled();
            case NEW -> {}
        }
    }

    public void cancelOrder(Order order){
        changeStatus(order, OrderStatus.CANCELED);
    }

    public List<Order> getOrders(SortOrder status) {
        Comparator<Order> comparator = switch (status){
            case DATE -> Comparator.comparing(Order::getDate, Comparator.nullsLast(Comparator.naturalOrder()));
            case COST -> Comparator.comparing(Order::getCost);
            case STATUS -> Comparator.comparing(Order::getStatus);
            case NONE -> null;
        };
        List<Order> res = new ArrayList<>(orders);
        if (comparator != null) res.sort(comparator);
        return res;
    }

    public List<Order> getCompletedOrders(LocalDate from, LocalDate to, SortOrder sort) {
        Comparator<Order> comparator = switch (sort){
            case DATE -> Comparator.comparing(Order::getDate);
            case COST -> Comparator.comparing(Order::getCost);
            case STATUS, NONE -> null;
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
