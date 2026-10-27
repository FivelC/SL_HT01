package model;

public class BookRequest {
    private final int id;
    private Book book;
    private RequestStatus status;
    private User castomer;

    public BookRequest(int number, Book book, User user){
        this(number, book, user, RequestStatus.OPEN);
    }

    public BookRequest(int number, Book book, User user, RequestStatus status){
        if (number <= 0) throw new IllegalArgumentException("Id запроса должен быть больше 0: " + number);
        if (book == null) throw new IllegalArgumentException("У запроса должна быть книга");
        if (status == null) throw new IllegalArgumentException("У запроса должен быть статус");
        this.id = number;
        this.book = book;
        this.castomer = user;
        this.status = status;
    }

    public void update(BookRequest other){
        if (other.id != this.id) throw new IllegalArgumentException("Нельзя обновить запрос чужими данными");
        this.book = other.book;
        this.castomer = other.castomer;
        this.status = other.status;
    }

    public void closed(){
        status = RequestStatus.CLOSED;
    }

    public boolean isOpen() {
        return status == RequestStatus.OPEN;
    }

    public Book getBook() {
        return book;
    }

    public int getId() {
        return id;
    }

    public User getCastomer() {
        return castomer;
    }

    public RequestStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Запрос #" + id + " на " + book.getName() + " - " + status;
    }
}
