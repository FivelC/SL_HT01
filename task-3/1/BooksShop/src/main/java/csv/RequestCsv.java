package csv;

import model.BookRequest;

public class RequestCsv implements ICsv<BookRequest>{
    private static final String HEADER =
            "id;bookId;firstName;lastName;status";
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
                request.getCastomer().firstName(),
                request.getCastomer().lastName(),
                String.valueOf(request.getStatus())
        );
    }

    @Override
    public BookRequest fromLine(String line) {
        return null;
    }
}
