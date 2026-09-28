import java.util.ArrayList;
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

    public List<Order> getOrders() {
        return List.copyOf(orders);
    }
}
