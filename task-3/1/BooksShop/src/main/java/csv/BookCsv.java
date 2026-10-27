package csv;

import model.Book;
import model.BookStatus;

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
        String[] parts = line.split(";",-1);

        if(parts.length != 11) throw new IllegalArgumentException("Должно быть 11 параметров");

        return new Book(Integer.parseInt(parts[0]),parts[1],parts[2],parseDate(parts[3]),parts[4],Float.parseFloat(parts[5]),
                Float.parseFloat(parts[6]),Boolean.parseBoolean(parts[7]), BookStatus.valueOf(parts[8]),
                parts[9],parseDate(parts[10]));
    }

    private LocalDate parseDate(String line){
        return line.isEmpty() ? null : LocalDate.parse(line);
    }
}
