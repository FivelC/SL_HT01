package controller.action;

import controller.ConsoleInput;
import controller.ShopProvider;
import csv.BookCsv;
import csv.ExportCsv;
import csv.OrderCsv;
import model.*;
import view.IAction;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ExportOrderAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        String fileName = input.readLine("Имя файла: ");

        List<Order> orders = shop.getOrders(SortOrder.NONE);

        try {
            ExportCsv.export(orders ,new OrderCsv(), Path.of(fileName));
            System.out.println("Экспорт: " +orders.size());
        } catch (IOException e) {
            System.out.println("Не получилось экспортировать: " +e.getMessage());
        }
    }
}
