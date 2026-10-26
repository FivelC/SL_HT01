package csv;

import model.Order;

import java.time.LocalDate;

public class OrderCsv implements ICsv<Order>{
    private static final String HEADER =
            "id;bookId;firstName;lastName;requestId;cost;date;status";

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
    @Override
    public Order fromLine(String line) {
        return null;
    }
}
