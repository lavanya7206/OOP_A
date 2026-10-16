import java.util.*;
public class AccountStore 
{
    private HashMap<String, Account> accounts = new HashMap<>();
    private ArrayList<String> transactionHistory = new ArrayList<>();
    public void add(Account account) 
    {
        accounts.put(account.getAccountNumber(), account);
    }
    public Account get(String accountNumber)
    {
        return accounts.get(accountNumber);
    }
    public ArrayList<Account> getAll() 
    {
        return new ArrayList<>(accounts.values());
    }
    public void addTransaction(String transaction) 
    {
        transactionHistory.add(transaction);
    }
    public ArrayList<String> getTransactionHistory() 
    {
        return new ArrayList<>(transactionHistory);
    }
    public ArrayList<Account> sortedByBalance() 
    {
        ArrayList<Account> list = getAll();
        Collections.sort(list, new Comparator<Account>() 
        {
            public int compare(Account a, Account b) 
            {
                return Double.compare(b.getBalance(), a.getBalance());
            }
        });
        return list;
    }
}