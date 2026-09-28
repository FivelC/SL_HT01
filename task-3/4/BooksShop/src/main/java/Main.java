import java.util.List;

public class Main {
    public static void main(String[] args){
        Book book1 = new Book("Мастер и Маргарита", "Булгаков", "Роман", 4.8f, 500, false, BookStatus.AVAILABLE);
        Book book2 = new Book("Война и мир", "Толстой", "Роман", 4.6f, 700, false, BookStatus.NOT_AVAILABLE);
        Book book3 = new Book("Преступление и наказание", "Достоевский", "Роман", 4.7f, 450, true, BookStatus.AVAILABLE);
        Book book4 = new Book("Идиот", "Достоевский", "Роман", 1.1f, 400, false, BookStatus.NOT_AVAILABLE);

        Shop shop = new Shop(new Storage(List.of(book1, book2, book3, book4)), new OrderProcessor(), new RequestProcessor());

        System.out.println("--- Заказ книги в наличии ---");
        Order order1 = shop.createOrder(book1);
        shop.changeOrderStatus(order1, OrderStatus.COMPLETED);
        System.out.println(order1);
        System.out.println('\n');

        System.out.println("--- Заказ отсутствующей книги ---");
        Order order2 = shop.createOrder(book2);
        System.out.println(order2);
        System.out.println(shop.getRequests());
        try {
            shop.changeOrderStatus(order2, OrderStatus.COMPLETED);
        } catch (IllegalStateException e){
            System.out.println("Ошибка: " + e.getMessage()); //закрыть заказ нельзя пока не выдал книгу
        }
        System.out.println('\n');

        System.out.println("--- Списание книги и запрос на неё ---");
        shop.removeBook(book3);
        System.out.println(book3);
        System.out.println(shop.createRequest(book3));
        System.out.println('\n');

        System.out.println("--- Отмена заказа ---");
        Order order3 = shop.createOrder(book3);
        shop.cancelOrder(order3);
        System.out.println(order3);
        System.out.println('\n');

        System.out.println("--- Поступление книг на склад ---");
        shop.storeBook(book2);
        shop.storeBook(book3);
        shop.changeOrderStatus(order2, OrderStatus.COMPLETED);
        System.out.println(order2);
        System.out.println('\n');

        System.out.println("--- Два заказа на одну отсутствующую книгу ---");
        Order order4 = shop.createOrder(book4);
        Order order5 = shop.createOrder(book4);
        System.out.println(order4);
        System.out.println(order5);
        for (BookRequest request : shop.getRequests()){
            if (request.getBook() == book4) System.out.println(request);
        }
        System.out.println("Книга поступила");
        shop.storeBook(book4);
        for (BookRequest request : shop.getRequests()){
            if (request.getBook() == book4) System.out.println(request);
        }
        shop.changeOrderStatus(order4, OrderStatus.COMPLETED);
        System.out.println(order4 + " | " + order5);
        shop.changeOrderStatus(order5, OrderStatus.COMPLETED);
        System.out.println(order4 + " | " + order5);
        System.out.println('\n');

        System.out.println("--- Итог ---");
        shop.getBooks().forEach(System.out::println);
        shop.getOrders().forEach(System.out::println);
        shop.getRequests().forEach(System.out::println);
    }
}
