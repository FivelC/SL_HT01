package controller.action;

import controller.ConsoleInput;
import controller.ShopProvider;
import csv.BookCsv;
import csv.ExportCsv;
import csv.ICsv;
import model.Book;
import model.Shop;
import model.SortBook;
import view.IAction;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ExportBookAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        String fileName = input.readLine("Имя файла: ");

        List<Book> books = shop.getBooks(SortBook.NONE);

        try {
            ExportCsv.export(books,new BookCsv(), Path.of(fileName));
            System.out.println("Экспорт: " +books.size());
        } catch (IOException e) {
            System.out.println("Не получилось экспортировать: " +e.getMessage());
        }
    }
}
