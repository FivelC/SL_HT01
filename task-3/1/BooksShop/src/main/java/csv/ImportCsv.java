package csv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ImportCsv {
    public static<T> List<T> importing(Path path, ICsv<T> csv) throws IOException {
        List<String> lines = Files.readAllLines(path);

        if(lines.isEmpty() || !lines.get(0).equals(csv.header())){
            throw new IllegalArgumentException("Неверный формат файла: ожидался заголовок" + csv.header());
        }

        List<T> result = new ArrayList<>();
        for(int i = 1; i < lines.size();i++){
            String line = lines.get(i);
            if(line.isBlank()) continue;
            try {
                result.add(csv.fromLine(line));
            } catch (IllegalArgumentException | DateTimeParseException e){
                throw new IllegalArgumentException("Ошибка в строке " + (i + 1) + ": " + e.getMessage());
            }
        }
        return result;
    }
}
