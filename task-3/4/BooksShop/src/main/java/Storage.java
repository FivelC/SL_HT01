import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Book> books = new ArrayList<>();

    public Storage(List<Book> books){
        this.books.addAll(books);
    }

    public void storeBook(Book book){
        checkBook(book);
        book.setStatus(BookStatus.AVAILABLE);
    }

    public void removeBook(Book book){
        checkBook(book);
        book.setStatus(BookStatus.NOT_AVAILABLE);
    }

    public boolean isAvailable(Book book){
        checkBook(book);
        return book.getStatus() == BookStatus.AVAILABLE;
    }

    public List<Book> getBooks() {
        return List.copyOf(books);
    }

    private void checkBook(Book book){
        if (!books.contains(book)) throw new IllegalArgumentException("Книги " + book.getName() + " нет в каталоге");
    }
}
