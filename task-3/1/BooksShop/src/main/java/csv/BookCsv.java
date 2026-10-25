package csv;

import model.Book;

public class BookCsv implements ICsv<Book> {
    private static final String SEPARATOR = ";";
    private static final String HEADER =
            "id;name;author;date;genre;rating;cost;ebook;status;description;arrivalDate";

    @Override
    public String header() {
        return "";
    }

    @Override
    public String toLine(Book entity) {
        return "";
    }

    @Override
    public Book fromLine(String line) {
        return null;
    }
}
