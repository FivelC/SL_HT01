package controller.action;

import controller.ConsoleInput;
import controller.ShopProvider;
import csv.ExportCsv;
import csv.OrderCsv;
import csv.RequestCsv;
import model.*;
import view.IAction;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ExportRequestAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        String fileName = input.readLine("Имя файла: ");

        List<BookRequest> requests = shop.getRequests();

        try {
            ExportCsv.export(requests ,new RequestCsv(), Path.of(fileName));
            System.out.println("Экспорт: " + requests.size());
        } catch (IOException e) {
            System.out.println("Не получилось экспортировать: " +e.getMessage());
        }
    }
}
