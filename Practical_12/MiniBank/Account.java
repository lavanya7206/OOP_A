public class Account implements Comparable<Account> 
{
    private String accountNumber;
    private String name;
    private double balance;
    public Account(String accountNumber, String name, double balance) 
    {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }
    public String getAccountNumber() 
    {
        return accountNumber;
    }
    public String getName() 
    {
        return name;
    }
    public double getBalance()
    {
        return balance;
    }
    public int compareTo(Account other) 
    {
        return this.accountNumber.compareTo(other.accountNumber);
    }
    public String toString() 
    {
        return accountNumber + " | " + name + " | Balance: " + balance;
    }
}
