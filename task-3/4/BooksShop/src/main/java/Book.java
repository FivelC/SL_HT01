public class Book {
    private boolean ebook;
    private String name;
    private String genre;
    private String author;
    private float rating;
    private float cost;
    private BookStatus status = BookStatus.AVAILABLE;

    public Book(String name, String author, String genre, float rating, float cost, boolean ebook, BookStatus status){
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
        this.cost = cost;
        this.ebook = ebook;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name + " (" + author + ") - " + status;
    }
}
