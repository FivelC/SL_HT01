import java.time.LocalDate;

public class Book {
    private final boolean ebook;
    private final String name;
    private final String genre;
    private final String author;
    private final LocalDate date;
    private LocalDate arrivalDate;
    private float rating;
    private float cost;
    private BookStatus status = BookStatus.AVAILABLE;
    private final String description;


    public Book(String name, String author, LocalDate date, String genre, float rating, float cost, boolean ebook, BookStatus status, String description, LocalDate arrivalDate){
        this.name = name;
        this.author = author;
        this.date = date;
        this.genre = genre;
        this.rating = rating;
        this.cost = cost;
        this.ebook = ebook;
        this.status = status;
        this.description = description;
        this.arrivalDate = arrivalDate;
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

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public float getCost() {
        return cost;
    }

    public float getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " (" + author + ") - " + status;
    }
}
