import java.util.concurrent.ConcurrentHashMap;
public class TransactionProcessor 
{
    private ConcurrentHashMap<String, Account> accounts =
            new ConcurrentHashMap<>();
    public void add(Account account) 
    {
        accounts.put(account.getAccountNumber(), account);
    }
    public Account get(String accountNumber) 
    {
        return accounts.get(accountNumber);
    }
}