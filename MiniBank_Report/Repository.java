import java.util.List;

public interface Repository<T> {
    void add(T item);
    T find(String number);
    List<T> findAll();
}