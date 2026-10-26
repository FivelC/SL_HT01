package csv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ExportCsv {
    public static<T> void export(List<T> items, ICsv<T> csv, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(csv.header());
        for(T item : items){
            lines.add(csv.toLine(item));
        }

        Files.write(file,lines);
    }
}
