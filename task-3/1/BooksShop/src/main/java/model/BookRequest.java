package model;

public class BookRequest {
    private final int id;
    private final Book book;
    private RequestStatus status = RequestStatus.OPEN;
    private User castomer;

    public BookRequest(int number, Book book, User user){
        this.id = number;
        this.book = book;
        this.castomer = user;
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
