package controller;

import model.*;

import java.time.LocalDate;
import java.util.List;

public class ShopProvider {
    private static ShopProvider instance;
    private final Shop shop;

    private ShopProvider(){
        Book book1 = new Book("Мастер и Маргарита", "Булгаков", LocalDate.of(1967, 1, 1), "Роман", 4.8f, 500, false, BookStatus.AVAILABLE,
                "Роман о визите дьявола в Москву 1930-х годов", LocalDate.of(2025, 1, 10));
        Book book2 = new Book("Война и мир", "Толстой", LocalDate.of(1869, 1, 1), "Роман", 4.6f, 700, false, BookStatus.NOT_AVAILABLE,
                "Роман-эпопея об эпохе наполеоновских войн", LocalDate.of(2024, 9, 1));
        Book book3 = new Book("Преступление и наказание", "Достоевский", LocalDate.of(1866, 1, 1), "Роман", 4.7f, 450, true, BookStatus.AVAILABLE,
                "История студента Раскольникова", LocalDate.of(2024, 11, 20));
        Book book4 = new Book("Идиот", "Достоевский", LocalDate.of(1869, 1, 1), "Роман", 1.1f, 400, false, BookStatus.NOT_AVAILABLE,
                "Роман о князе Мышкине", LocalDate.of(2024, 10, 5));
        Book book5 = new Book("Отцы и дети", "Тургенев", LocalDate.of(1862, 1, 1), "Роман", 4.3f, 350, false, BookStatus.AVAILABLE,
                "Роман о конфликте поколений", LocalDate.of(2025, 3, 1));
        shop = new Shop(new Storage(List.of(book1,book2,book3,book4,book5)), new OrderProcessor(), new RequestProcessor());
    }

    public static ShopProvider getInstance(){
        if (instance == null){
             instance = new ShopProvider();
        }
        return instance;
    }

    public Shop getShop() { return shop; }
}
