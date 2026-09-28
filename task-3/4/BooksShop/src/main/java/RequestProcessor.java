import java.util.ArrayList;
import java.util.List;

public class RequestProcessor {
    private List<BookRequest> requests = new ArrayList<>();
    private int lastRequestNumber = 0;

    public BookRequest createRequest(Book book){
        BookRequest request = new BookRequest(++lastRequestNumber, book);
        requests.add(request);
        return request;
    }

    public void closeRequests(Book book){
        for (BookRequest request : requests){
            if (request.getBook() == book && request.isOpen()) request.closed();
        }
    }

    public List<BookRequest> getRequests() {
        return List.copyOf(requests);
    }
}
