import java.io.Serializable;
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    int accountNumber;
    Customer customer;
    long balance;
    public Account(int accountNumber, Customer customer,long balance) 
    {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
    }
    public void deposit(long amount)
    {
        balance += amount;
    }
    public void withdraw(long amount)
    {
        if (amount <= balance) 
        {
            balance -= amount;
        }
    }
    public String toString()
    {
        return "Account No: " + accountNumber +", " + customer +", Balance: Rs. " + balance;
    }
}
