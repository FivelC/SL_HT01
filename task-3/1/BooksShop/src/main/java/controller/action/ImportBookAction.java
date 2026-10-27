package controller.action;

import controller.ConsoleInput;
import controller.ShopProvider;
import csv.BookCsv;
import csv.ImportCsv;
import model.*;
import view.IAction;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

public class ImportBookAction implements IAction {
    @Override
    public void execute() {
        ConsoleInput input = ConsoleInput.getInstance();
        Shop shop = ShopProvider.getInstance().getShop();

        String fileName = input.readLine("Имя файла: ");

        try {
            List<Book> items = ImportCsv.importing(Path.of(fileName), new BookCsv());
            shop.importBooks(items);
            System.out.println("Импортировано книг: " + items.size());
        } catch (NoSuchFileException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Импорт отменён, данные не изменены. " + e.getMessage());
        }
    }
}
