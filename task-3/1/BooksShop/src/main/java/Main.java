//import model.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args){
//        Book book1 = new Book("Мастер и Маргарита", "Булгаков", LocalDate.of(1967, 1, 1), "Роман", 4.8f, 500, false, BookStatus.AVAILABLE,
//                "Роман о визите дьявола в Москву 1930-х годов", LocalDate.of(2025, 1, 10));
//        Book book2 = new Book("Война и мир", "Толстой", LocalDate.of(1869, 1, 1), "Роман", 4.6f, 700, false, BookStatus.NOT_AVAILABLE,
//                "Роман-эпопея об эпохе наполеоновских войн", LocalDate.of(2024, 9, 1));
//        Book book3 = new Book("Преступление и наказание", "Достоевский", LocalDate.of(1866, 1, 1), "Роман", 4.7f, 450, true, BookStatus.AVAILABLE,
//                "История студента Раскольникова", LocalDate.of(2024, 11, 20));
//        Book book4 = new Book("Идиот", "Достоевский", LocalDate.of(1869, 1, 1), "Роман", 1.1f, 400, false, BookStatus.NOT_AVAILABLE,
//                "Роман о князе Мышкине", LocalDate.of(2024, 10, 5));
//        Book book5 = new Book("Отцы и дети", "Тургенев", LocalDate.of(1862, 1, 1), "Роман", 4.3f, 350, false, BookStatus.AVAILABLE,
//                "Роман о конфликте поколений", LocalDate.of(2025, 3, 1));
//
//        Shop shop = new Shop(new Storage(List.of(book1, book2, book3, book4, book5)), new OrderProcessor(), new RequestProcessor());
//
//        User ivan = new User("Иван", "Петров");
//        User anna = new User("Анна", "Смирнова");
//
//        System.out.println("--- Заказ книги в наличии ---");
//        Order order1 = shop.createOrder(book1, ivan);
//        shop.changeOrderStatus(order1, OrderStatus.COMPLETED);
//        System.out.println(order1);
//        System.out.println('\n');
//
//        System.out.println("--- Заказ отсутствующей книги ---");
//        Order order2 = shop.createOrder(book2, anna);
//        System.out.println(order2);
//        System.out.println(shop.getRequests());
//        try {
//            shop.changeOrderStatus(order2, OrderStatus.COMPLETED);
//        } catch (IllegalStateException e){
//            System.out.println("Ошибка: " + e.getMessage());
//        }
//        System.out.println('\n');
//
//        System.out.println("--- Списание книги и запрос на неё ---");
//        shop.removeBook(book3);
//        System.out.println(book3);
//        System.out.println(shop.createRequest(book3));
//        System.out.println('\n');
//
//        System.out.println("--- Отмена заказа ---");
//        Order order3 = shop.createOrder(book3, ivan);
//        shop.cancelOrder(order3);
//        System.out.println(order3);
//        System.out.println('\n');
//
//        System.out.println("--- Поступление книг на склад ---");
//        shop.storeBook(book2);
//        shop.storeBook(book3, LocalDate.of(2025, 2, 1));
//        shop.completeOrder(order2, LocalDate.of(2026, 1, 15));
//        System.out.println(order2);
//        System.out.println('\n');
//
//        System.out.println("--- Два заказа на одну отсутствующую книгу ---");
//        Order order4 = shop.createOrder(book4, ivan);
//        Order order5 = shop.createOrder(book4, anna);
//        System.out.println(order4);
//        System.out.println(order5);
//        for (BookRequest request : shop.getRequests()){
//            if (request.getBook() == book4) System.out.println(request);
//        }
//        System.out.println("Книга поступила");
//        shop.storeBook(book4);
//        for (BookRequest request : shop.getRequests()){
//            if (request.getBook() == book4) System.out.println(request);
//        }
//        shop.completeOrder(order4, LocalDate.of(2025, 12, 1));
//        System.out.println(order4 + " | " + order5);
//        shop.changeOrderStatus(order5, OrderStatus.COMPLETED);
//        System.out.println(order4 + " | " + order5);
//        System.out.println('\n');
//
//        System.out.println("--- Итог ---");
//        shop.getBooks(SortBook.NONE).forEach(System.out::println);
//        shop.getOrders(SortOrder.NONE).forEach(System.out::println);
//        shop.getRequests().forEach(System.out::println);
//
//        System.out.println("--- Сортировки ---");
//        shop.getBooks(SortBook.ALPHABET).forEach(System.out::println);
//        System.out.println('\n');
//        shop.getBooks(SortBook.DATE).forEach(System.out::println);
//        System.out.println('\n');
//        shop.getBooks(SortBook.COST).forEach(System.out::println);
//        System.out.println('\n');
//        shop.getBooks(SortBook.AVAILABLE).forEach(System.out::println);
//        System.out.println('\n');
//
//        System.out.println("--- Заказы ---");
//        shop.getOrders(SortOrder.DATE).forEach(System.out::println);
//        System.out.println('\n');
//        shop.getOrders(SortOrder.COST).forEach(System.out::println);
//        System.out.println('\n');
//        shop.getOrders(SortOrder.STATUS).forEach(System.out::println);
//        System.out.println('\n');
//
//        System.out.println("--- Запросы ---");
//        shop.getRequests(SortRequest.NUMBER_REQUEST).forEach(System.out::println);
//        System.out.println('\n');
//        shop.getRequests(SortRequest.ALPHABET).forEach(System.out::println);
//        System.out.println('\n');
//
//        System.out.println("--- Выполненные заказы за период ---");
//        LocalDate from = LocalDate.of(2025, 1, 1);
//        LocalDate to = LocalDate.now();
//        shop.getCompletedOrders(from, to, SortOrder.DATE).forEach(System.out::println);
//        System.out.println('\n');
//        shop.getCompletedOrders(from, to, SortOrder.COST).forEach(System.out::println);
//        System.out.println('\n');
//
//        System.out.println("--- Заработано и количество выполненных заказов за период ---");
//        System.out.println("С " + from + " по " + to + ": заработано " + shop.getEarnedAmount(from, to)
//                + ", выполнено заказов: " + shop.getTotalOrders(from, to));
//        LocalDate from2026 = LocalDate.of(2026, 1, 1);
//        System.out.println("С " + from2026 + " по " + to + ": заработано " + shop.getEarnedAmount(from2026, to)
//                + ", выполнено заказов: " + shop.getTotalOrders(from2026, to));
//        System.out.println('\n');
//
//        System.out.println("--- Залежавшиеся книги (не продавались больше 6 месяцев) ---");
//        shop.getUnsoldBooks(SortUnsoldBook.DATE).forEach(System.out::println);
//        System.out.println('\n');
//        shop.getUnsoldBooks(SortUnsoldBook.COST).forEach(System.out::println);
//        System.out.println('\n');
//
//        System.out.println("--- Детали заказа ---");
//        System.out.println(shop.getOrderInfo(order1));
//        System.out.println();
//        System.out.println(shop.getOrderInfo(order3));
//        System.out.println('\n');
//
//        System.out.println("--- Описание книги ---");
//        System.out.println(book1.getName() + ": " + shop.getBookDescription(book1));
//        System.out.println(book5.getName() + ": " + shop.getBookDescription(book5));
//    }

import controller.Builder;
import controller.MenuController;
import view.Navigator;

class Main{
    public static void main(String[] args){
        Builder builder = new Builder();
        builder.buildMenu();
        MenuController menu = new MenuController(builder);
        menu.run();
    }
}
