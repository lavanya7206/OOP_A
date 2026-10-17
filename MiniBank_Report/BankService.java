import java.util.*;
public class BankService 
{
    private Repository<Account> repository;
    private Validator validator;
    private List<String> statement = new ArrayList<>();
    public BankService(Repository<Account> repository,Validator validator) 
    {
        this.repository = repository;
        this.validator = validator;
    }
    public void openAccount(String number, String name,double openingBalance) 
    {
        validator.checkAmount(openingBalance);
        if (repository.find(number) != null) 
        {
            throw new IllegalArgumentException("Account number already exists.");
        }
        Account account =new SavingsAccount(number, name, openingBalance);
        repository.add(account);
        statement.add("Account opened: " + number);
        System.out.println("Account opened successfully.");
    }
    public void deposit(String number, double amount) 
    {
        validator.checkAmount(amount);
        Account account = repository.find(number);
        validator.checkAccount(account);
        account.deposit(amount);
        statement.add(number + ": Deposit Rs. " + amount);
        System.out.println("Amount deposited successfully.");
    }
    public void withdraw(String number, double amount) 
    {
        validator.checkAmount(amount);
        Account account = repository.find(number);
        validator.checkAccount(account);
        synchronized (account) 
        {
            double remaining = account.getBalance() - amount;
            if (remaining < 500) 
            {
                throw new IllegalArgumentException("Minimum balance of Rs. 500 required.");
            }
            account.withdraw(amount);
        }
        statement.add(number + ": Withdrawal Rs. " + amount);
        System.out.println("Withdrawal successful.");
    }
    public void transfer(String from, String to, double amount) 
    {
        validator.checkAmount(amount);
        Account sender = repository.find(from);
        Account receiver = repository.find(to);
        validator.checkAccount(sender);
        validator.checkAccount(receiver);
        if (from.equals(to)) 
        {
            throw new IllegalArgumentException("Both accounts cannot be the same.");
        }
        Account first = from.compareTo(to) < 0 ? sender : receiver;
        Account second = from.compareTo(to) < 0 ? receiver : sender;
        synchronized (first) 
        {
            synchronized (second) 
            {
                if (sender.getBalance() - amount < 500) 
                {
                    throw new IllegalArgumentException( "Not enough balance to transfer.");
                }
                sender.withdraw(amount);
                receiver.deposit(amount);
            }
        }
        statement.add("Transfer Rs. " + amount + " from "+ from + " to " + to);
        System.out.println("Transfer successful.");
    }
    public void statement() 
    {
        if (statement.isEmpty()) 
        {
            System.out.println("No transactions yet.");
            return;
        }
        for (String entry : statement) 
        {
            System.out.println(entry);
        }
    }
    public void listAccounts() 
    {
        for (Account account : repository.findAll()) 
        {
            System.out.println(account);
        }
    }
    public void printReport() 
    {
        List<Account> all = repository.findAll();
        double total = 0;
        for (Account account : all) 
        {
            total += account.getBalance();
        }
        System.out.println("\n----- BANK REPORT -----");
        System.out.println("Total accounts: " + all.size());
        System.out.println("Total bank balance: Rs. " + total);
        System.out.println("Transactions: " + statement.size());
    }
}