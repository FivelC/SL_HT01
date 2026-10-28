package model;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RequestProcessor {
    private List<BookRequest> requests = new ArrayList<>();
    private int lastRequestNumber = 0;

    public BookRequest createRequest(Book book, User user){
        BookRequest request = new BookRequest(++lastRequestNumber, book, user);
        requests.add(request);
        return request;
    }

    public void closeRequests(Book book){
        for (BookRequest request : requests){
            if (request.getBook() == book && request.isOpen()) request.closed();
        }
    }

    public void closeRequest(BookRequest request){
        for(BookRequest br : requests){
            if(br.getId() == request.getId()) request.closed();
        }
    }

    public BookRequest findById(int id) {
        for (BookRequest request : requests) {
            if (request.getId() == id) return request;
        }
        return null;
    }

    public void importRequest(BookRequest request) {
        BookRequest existing = findById(request.getId());
        if (existing != null) {
            existing.update(request);
        } else {
            requests.add(request);
        }
        lastRequestNumber = Math.max(lastRequestNumber, request.getId());
    }

    public List<BookRequest> getRequests() {
        return List.copyOf(requests);
    }

    public List<RequestInfo> getRequests(SortRequest sort) {
        Map<Book, Long> counts = new LinkedHashMap<>();
        for (BookRequest request : requests){
            counts.merge(request.getBook(), 1L, Long::sum);
        }

        Collator ru = Collator.getInstance(Locale.forLanguageTag("ru"));
        Comparator<RequestInfo> comparator = switch (sort){
            case SortRequest.NUMBER_REQUEST -> Comparator.comparingLong(RequestInfo::getCount).reversed();
            case SortRequest.ALPHABET -> Comparator.comparing(info -> info.getBook().getName(), ru);
            case SortRequest.NONE -> null;
        };

        List<RequestInfo> res = new ArrayList<>();
        counts.forEach((book, count) -> res.add(new RequestInfo(book, count)));
        if (comparator != null) res.sort(comparator);
        return res;
    }
}
