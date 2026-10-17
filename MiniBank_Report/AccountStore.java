import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
public class AccountStore
        implements Repository<Account>, java.io.Serializable 
        {
    private static final long serialVersionUID = 1L;
    private final Map<String, Account> accounts =new ConcurrentHashMap<>();
    public void add(Account account) 
    {
        accounts.put(account.getNumber(), account);
    }
    public Account find(String number) 
    {
        return accounts.get(number);
    }
    public List<Account> findAll() 
    {
        return new ArrayList<>(accounts.values());
    }
        }