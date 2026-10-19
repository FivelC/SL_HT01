import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

public class Order {
    private static final long MIN_DATE = LocalDate.of(2000, 1, 1).toEpochDay();
    private static final long MAX_DATE = LocalDate.of(2026, 12, 31).toEpochDay();
    private final int number;
    private final Book book;

    private final float cost;
    private LocalDate date = null;
    private final BookRequest request;
    private OrderStatus status = OrderStatus.NEW;

    public Order(int number, Book book, BookRequest request){
        this.number = number;
        this.book = book;
        this.request = request;
        this.cost = book.getCost();
    }

    public void completed(){
        long randomDay = ThreadLocalRandom.current().nextLong(MIN_DATE, MAX_DATE);
        status = OrderStatus.COMPLETED;
        date = LocalDate.ofEpochDay(randomDay);
    }
    public void canceled(){
        status = OrderStatus.CANCELED;
    }

    public boolean isWaitingForBook() {
        return request != null && request.isOpen();
    }

    public int getNumber() {
        return number;
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

    @Override
    public String toString() {
        return "Заказ #" + number + " на " + book.getName() + " (" + cost + ", " + date + ") - " + status;
    }
}
