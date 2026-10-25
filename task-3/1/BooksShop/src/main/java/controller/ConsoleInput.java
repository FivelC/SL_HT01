package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeParseException;


public class ConsoleInput {
    private static ConsoleInput instance;
    private final Scanner scanner = new Scanner(System.in);

    private ConsoleInput() {
    }

    public static ConsoleInput getInstance() {
        if (instance == null) {
            instance = new ConsoleInput();
        }
        return instance;
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        while (true) {
            String line = readLine(prompt);
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
            }
        }
    }

    public <T> T choose(String title, List<T> options) {
        if (options.isEmpty()) {
            System.out.println("Список пуст");
            return null;
        }
        System.out.println(title);
        for (int i = 0; i < options.size(); i++) {
            System.out.println(i + ": " + options.get(i));
        }
        while (true) {
            int index = readInt("Ваш выбор: ");
            if (index >= 0 && index < options.size()) {
                return options.get(index);
            }
            System.out.println("Нет такого пункта");
        }
    }

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public LocalDate readDate(String prompt) {
        while (true) {
            String line = readLine(prompt);
            try {
                return LocalDate.parse(line, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Неверная дата, формат: дд.мм.гггг");
            }
        }
    }
}
