public class Order {
    private final int number;
    private final Book book;
    private final BookRequest request;
    private OrderStatus status = OrderStatus.NEW;

    public Order(int number, Book book, BookRequest request){
        this.number = number;
        this.book = book;
        this.request = request;
    }

    public void completed(){
        status = OrderStatus.COMPLETED;
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

    public Book getBook() {
        return book;
    }

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Заказ #" + number + " на " + book.getName() + " - " + status;
    }
}
