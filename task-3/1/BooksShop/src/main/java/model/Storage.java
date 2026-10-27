package model;

import java.text.Collator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Storage {
    private List<Book> books = new ArrayList<>();

    public Storage(List<Book> books){
        this.books.addAll(books);
    }

    public void storeBook(Book book){
        storeBook(book, LocalDate.now());
    }

    public void storeBook(Book book, LocalDate arrivalDate){
        checkBook(book);
        book.setStatus(BookStatus.AVAILABLE);
        book.setArrivalDate(arrivalDate);
    }

    public void importBook(Book book) {
        Book existing = findById(book.getId());
        if (existing != null) {
            existing.update(book);
        } else {
            books.add(book);
        }
    }

    public Book findById(int id) {
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == id) return books.get(i);
        }
        return null;
    }

    public void removeBook(Book book){
        checkBook(book);
        book.setStatus(BookStatus.NOT_AVAILABLE);
    }

    public boolean isAvailable(Book book){
        checkBook(book);
        return book.getStatus() == BookStatus.AVAILABLE;
    }

    public String getDescription(Book book){
        checkBook(book);
        return book.getDescription();
    }

    public List<Book> getBooks(SortBook status) {
        Collator ru = Collator.getInstance(Locale.forLanguageTag("ru"));
        Comparator<Book> comparator = switch (status) {
            case ALPHABET -> Comparator.comparing(Book::getName, ru);
            case DATE -> Comparator.comparing(Book::getDate);
            case COST -> Comparator.comparing(Book::getCost);
            case AVAILABLE -> Comparator.comparing(Book::getStatus).thenComparing(Book::getName, ru);
            case NONE -> null;
        };

        List<Book> res = new ArrayList<>(books);
        if (comparator != null) res.sort(comparator);
        return res;
    }

    private void checkBook(Book book){
        if (!books.contains(book)) throw new IllegalArgumentException("Книги " + book.getName() + " нет в каталоге");
    }
}
