package csv;

public interface ICsv<T>{
    String SEPARATOR = ";";
    String header();
    String toLine(T entity);
    T fromLine(String line);
}
