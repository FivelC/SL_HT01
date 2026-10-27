package csv;

import model.*;

import java.time.LocalDate;

public class OrderCsv implements ICsv<Order>{
    private static final String HEADER =
            "id;bookId;firstName;lastName;requestId;cost;date;status";

    private final Shop shop;

    public OrderCsv() {
        this(null);
    }

    public OrderCsv(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String header() {
        return HEADER;
    }

    @Override
    public String toLine(Order order) {
        return String.join(
                SEPARATOR,
                String.valueOf(order.getId()),
                String.valueOf(order.getBook().getId()),
                order.getCustomer().firstName(),
                order.getCustomer().lastName(),
                order.getRequest() == null ? "" : String.valueOf(order.getRequest().getId()),
                String.valueOf(order.getCost()),
                dateToString(order.getDate()),
                String.valueOf(order.getStatus())
        );
    }
    private String dateToString(LocalDate date) {
        return date == null ? "" : date.toString();
    }
    private LocalDate parseDate(String s) {
        return s.isEmpty() ? null : LocalDate.parse(s);
    }

    @Override
    public Order fromLine(String line) {
        if (shop == null) {
            throw new IllegalStateException("OrderCsv создан без магазина — импорт невозможен");
        }

        String[] parts = line.split(SEPARATOR,-1);
        if (parts.length != 8) throw new IllegalArgumentException("Должно быть 8 параметров, получено " + parts.length);

        int id = Integer.parseInt(parts[0]);
        int bookId = Integer.parseInt(parts[1]);
        Book book = shop.findBookById(bookId);

        if(book == null)
            throw new IllegalArgumentException("Книга с id=" + bookId + " не найдена (сначала импортируйте книги)");

        User customer = parts[2].isEmpty() && parts[3].isEmpty() ? null : new User(parts[2], parts[3]);

        BookRequest request = null;
        if (!parts[4].isEmpty()) {
            int requestId = Integer.parseInt(parts[4]);
            request = shop.findRequestById(requestId);
            if (request == null)
                throw new IllegalArgumentException("Запрос с id=" + requestId + " не найден");
        }
        float cost = Float.parseFloat(parts[5]);

        LocalDate date = null;
        if(!parts[6].isEmpty()) {
            date = LocalDate.parse(parts[6]);
        }
        OrderStatus status = OrderStatus.valueOf(parts[7]);
        return new Order(id, book, customer,request,cost,date, status);
    }
}
