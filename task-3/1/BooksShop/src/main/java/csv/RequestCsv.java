package csv;

import model.Book;
import model.BookRequest;
import model.RequestStatus;
import model.Shop;
import model.User;

public class RequestCsv implements ICsv<BookRequest>{
    private static final String HEADER =
            "id;bookId;firstName;lastName;status";

    private final Shop shop;

    public RequestCsv() {
        this(null);
    }

    public RequestCsv(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String header() {
        return HEADER;
    }

    @Override
    public String toLine(BookRequest request) {
        return String.join(
                SEPARATOR,
                String.valueOf(request.getId()),
                String.valueOf(request.getBook().getId()),
                request.getCastomer() == null ? "" : request.getCastomer().firstName(),
                request.getCastomer() == null ? "" : request.getCastomer().lastName(),
                String.valueOf(request.getStatus())
        );
    }

    @Override
    public BookRequest fromLine(String line) {
        if (shop == null) {
            throw new IllegalStateException("RequestCsv создан без магазина - импорт невозможен");
        }
        String[] parts = line.split(SEPARATOR, -1);
        if (parts.length != 5) throw new IllegalArgumentException("Должно быть 5 параметров, получено " + parts.length);

        int id = Integer.parseInt(parts[0]);
        int bookId = Integer.parseInt(parts[1]);

        Book book = shop.findBookById(bookId);
        if (book == null) throw new IllegalArgumentException("Книга с id=" + bookId + " не найдена (сначала импортируйте книги)");

        User customer = parts[2].isEmpty() && parts[3].isEmpty() ? null : new User(parts[2], parts[3]);
        RequestStatus status = RequestStatus.valueOf(parts[4]);

        return new BookRequest(id, book, customer, status);
    }
}
