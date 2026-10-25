package model;

public class BookRequest {
    private final int id;
    private final Book book;
    private RequestStatus status = RequestStatus.OPEN;

    public BookRequest(int number, Book book){
        this.id = number;
        this.book = book;
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

    public RequestStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Запрос #" + id + " на " + book.getName() + " - " + status;
    }
}
