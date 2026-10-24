package model;

public class BookRequest {
    private final int number;
    private final Book book;
    private RequestStatus status = RequestStatus.OPEN;

    public BookRequest(int number, Book book){
        this.number = number;
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

    public RequestStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Запрос #" + number + " на " + book.getName() + " - " + status;
    }
}
