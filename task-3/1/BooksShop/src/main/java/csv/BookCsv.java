package csv;

import model.Book;

import java.time.LocalDate;

public class BookCsv implements ICsv<Book> {
    private static final String HEADER =
            "id;name;author;date;genre;rating;cost;ebook;status;description;arrivalDate";

    @Override
    public String header() {
        return HEADER;
    }

    @Override
    public String toLine(Book book) {
        return String.join(SEPARATOR,
                String.valueOf(book.getId()),
                book.getName(),
                book.getAuthor(),
                dateToString(book.getDate()),
                book.getGenre(),
                String.valueOf(book.getRating()),
                String.valueOf(book.getCost()),
                String.valueOf(book.isEbook()),
                String.valueOf(book.getStatus()),
                book.getDescription(),
                dateToString(book.getArrivalDate())
        );
    }
    private String dateToString(LocalDate date) {
        return date == null ? "" : date.toString();
    }
    @Override
    public Book fromLine(String line) {
        return null;
    }
}
