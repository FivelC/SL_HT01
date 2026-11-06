package persistence;

import model.Book;
import model.BookStatus;

import java.time.LocalDate;

public record BookData(int id, String name, String author, LocalDate date, String genre,
                       float rating, float cost, boolean ebook, BookStatus status,
                       String description, LocalDate arrivalDate) {

    public static BookData from(Book b) {
        return new BookData(b.getId(), b.getName(), b.getAuthor(), b.getDate(), b.getGenre(),
                b.getRating(), b.getCost(), b.isEbook(), b.getStatus(),
                b.getDescription(), b.getArrivalDate());
    }

    public Book toBook() {
        return new Book(id, name, author, date, genre, rating, cost, ebook, status, description, arrivalDate);
    }
}