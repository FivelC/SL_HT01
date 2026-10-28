package config;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ShopConfig {
    private static final int DEFAULT_UNSOLD_MONTHS = 6;
    private static final boolean DEFAULT_AUTO_CLOSE = true;

    private final Properties props = new Properties();

    public ShopConfig(Path file) {
        try (Reader reader = Files.newBufferedReader(file)) {
            props.load(reader);
        } catch (IOException e) {
            System.out.println("Не удалось прочитать " + file.toAbsolutePath() + ", используются настройки по умолчанию");
        }
    }

    public int getUnsoldMonths() {
        String value = props.getProperty("shop.unsold.months");
        if (value == null) return DEFAULT_UNSOLD_MONTHS;

        value = value.trim();
        try {
            int res = Integer.parseInt(value);
            if (res > 0) return res;
        } catch (NumberFormatException e) {
            System.out.println("Введено не число: " + value);
        }
        System.out.println("Неверное значение shop.unsold.months=" + value + ", используется " + DEFAULT_UNSOLD_MONTHS);
        return DEFAULT_UNSOLD_MONTHS;
    }

    public boolean isAutoCloseRequests() {
        String value = props.getProperty("shop.requests.autoClose");
        if (value == null) return DEFAULT_AUTO_CLOSE;
        value = value.trim();
        if (value.equalsIgnoreCase("true")) return true;
        if (value.equalsIgnoreCase("false")) return false;
        System.out.println("Неверное значение shop.requests.autoClose=" + value + ", используется " + DEFAULT_AUTO_CLOSE);
        return DEFAULT_AUTO_CLOSE;
    }
}