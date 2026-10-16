import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Id {}
class Account
{
    @Id
    private String id;
    private String name;
    private double balance;
    Account(String id, String name, double balance)
    {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    public String toString()
    {
        return id + " " + name + " " + balance;
    }
}
class Repository<T>
{
    private HashMap<String, T> data = new HashMap<>();
    T save(T entity)
    {
        try
        {
            for (Field field : entity.getClass().getDeclaredFields())
            {
                if (field.isAnnotationPresent(Id.class))
                {
                    field.setAccessible(true);
                    String id = field.get(entity).toString();
                    data.put(id, entity);
                    return entity;
                }
            }
            throw new IllegalArgumentException("No @Id field found");
        } 
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }
    @SafeVarargs
    public final void saveAll(T... items)
    {
        for (T item : items)
        {
            save(item);
        }
    }
    T findById(String id)
    {
        return data.get(id);
    }
    ArrayList<T> findAll()
    {
        return new ArrayList<>(data.values());
    }
    boolean delete(String id)
    
        return data.remove(id) != null;
    }
    static <N extends Number> double average(N[] values) 
    {
        if (values.length == 0)
        {
            return 0;
        }
        double sum = 0;
        for (N n : values) 
        {
            sum += n.doubleValue();
        }
        return sum / values.length;
    }
}
public class MiniBank 
{
    public static void main(String[] args)
    {
        Repository<Account> repo = new Repository<>();
        Account a1 = new Account("101", "Aman", 5000);
        Account a2 = new Account("102", "Riya", 7000);
        Account a3 = new Account("103", "Raj", 9000);
        repo.saveAll(a1, a2, a3);
        System.out.println("Account found:");
        System.out.println(repo.findById("102"));
        System.out.println("\nAll Accounts:");
        for (Account a : repo.findAll()) 
        {
            System.out.println(a);
        }
        Integer[] numbers = {10, 20, 30};
        System.out.println("\nAverage: " + Repository.average(numbers));
    }
}
