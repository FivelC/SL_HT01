package model;

public class RequestInfo {
    private final Book book;
    private final long count;

    public RequestInfo(Book book, long count) {
        this.book = book;
        this.count = count;
    }

    public Book getBook() {
        return book;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return book.getName() + " - запросов: " + count;
    }
}
