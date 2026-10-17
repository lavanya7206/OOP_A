import java.io.Serializable;
public class Account implements Serializable 
{
    private static final long serialVersionUID = 1L;
    protected String number;
    protected String holder;
    protected double balance;
    public Account(String number, String holder, double balance) 
    {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
    }
    public String getNumber() 
    {
        return number;
    }
    public String getHolder() 
    {
        return holder;
    }
    public synchronized double getBalance() 
    {
        return balance;
    }
    public synchronized void deposit(double amount) 
    {
        balance = balance + amount;
    }
    public synchronized void withdraw(double amount) 
    {
        balance = balance - amount;
    }
    @Override
    public String toString() 
    {
        return number + " | " + holder + " | Rs. " + balance;
    }
}
