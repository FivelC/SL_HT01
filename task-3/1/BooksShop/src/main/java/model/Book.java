package model;

import java.time.LocalDate;

public class Book {
    private boolean ebook;
    private String name;
    private String genre;
    private String author;
    private LocalDate date;
    private LocalDate arrivalDate;
    private float rating;
    private float cost;
    private BookStatus status = BookStatus.AVAILABLE;
    private String description;
    private final int id;

    public Book(int id,String name, String author, LocalDate date, String genre,
                float rating, float cost, boolean ebook, BookStatus status,
                String description, LocalDate arrivalDate){
        if(id <= 0) throw new IllegalArgumentException("Id не может быть отрицательным: " + id);
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Пустое название книги");
        if (author == null || author.isBlank()) throw new IllegalArgumentException("Пустое поле с автором");
        if (genre == null || genre.isBlank()) throw new IllegalArgumentException("Пустое поле с жанром");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("Пустое поле с описанием");
        if (rating > 5.0 || rating <0.0) throw new IllegalArgumentException("Рейтинг должен быть от 0.0 до 5.0");
        if (cost < 0.0) throw new IllegalArgumentException("Цена не должна быть ниже 0.0");

        LocalDate today = LocalDate.now();

        if (date != null && date.isAfter(today)) {
            throw new IllegalArgumentException("Дата издания в будущем: " + date);
        }
        if (arrivalDate != null && arrivalDate.isAfter(today)) {
            throw new IllegalArgumentException("Дата поступления в будущем: " + arrivalDate);
        }
        if (date != null && arrivalDate != null && arrivalDate.isBefore(date)) {
            throw new IllegalArgumentException("Книга поступила раньше, чем была издана: " + arrivalDate);
        }

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
        this.id = id;
    }

    public void update(Book other){
        if (other.id != this.id) throw new IllegalArgumentException("Нельзя обновить книгу чужими данными");
        this.name = other.name;
        this.author = other.author;
        this.date = other.date;
        this.genre = other.genre;
        this.rating = other.rating;
        this.cost = other.cost;
        this.ebook = other.ebook;
        this.status = other.status;
        this.description = other.description;
        this.arrivalDate = other.arrivalDate;
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

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isEbook() {
        return ebook;
    }

    @Override
    public String toString() {
        return name + " (" + author + ") - " + status;
    }
}
