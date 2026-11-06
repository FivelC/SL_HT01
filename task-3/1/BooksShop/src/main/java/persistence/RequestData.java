package persistence;

import model.BookRequest;
import model.RequestStatus;
import model.Shop;
import model.User;

public record RequestData(int id, int bookId, String firstName,
                          String lastName, RequestStatus status) {
    public static RequestData from(BookRequest req){
        return new RequestData(req.getId(), req.getBook().getId(),req.getCastomer().firstName(),req.getCastomer().lastName(),
                req.getStatus());
    }
    public BookRequest toRequest(Shop shop){
        return new BookRequest(id, shop.findBookById(bookId), new User(firstName,lastName),status);
    }
}
