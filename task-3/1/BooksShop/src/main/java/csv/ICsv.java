package csv;

public interface ICsv<T>{
    String header();
    String toLine(T entity);
    T fromLine(String line);
}
