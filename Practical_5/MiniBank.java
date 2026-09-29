abstract class Account 
{
    String name;
    long accountNumber;
    long balance;
    Account(String name, long accountNumber, long balance) 
    {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    abstract double interestRate();
    abstract boolean canWithdraw(long amount);
}
class SavingsAccount extends Account 
{
    long minBalance;
    SavingsAccount(String name, long accountNumber, long balance, long minBalance) 
    {
        super(name, accountNumber, balance);
        this.minBalance = minBalance;
    }
    double interestRate()
    {
        return 4.0;
    }
    boolean canWithdraw(long amount)
    {
        return balance - amount >= minBalance;
    }
}
class CurrentAccount extends Account
{
    long overdraftLimit;
    CurrentAccount(String name, long accountNumber, long balance, long overdraftLimit) 
    {
        super(name, accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }
    double interestRate()
    {
        return 0.0;
    }
    boolean canWithdraw(long amount)
    {
        return balance - amount >= -overdraftLimit;
    }
}
class FixedDepositAccount extends Account
{
    FixedDepositAccount(String name, long accountNumber, long balance) 
    {
        super(name, accountNumber, balance);
    }
    double interestRate() 
    {
        return 7.0;
    }
    boolean canWithdraw(long amount) 
    {
        return false;
    }
}
public class MiniBank 
{
    public static void main(String[] args) 
    {
        Account[] accounts = {
            new SavingsAccount("Rahul", 101, 20000, 5000),
            new CurrentAccount("Amit", 102, 10000, 5000),
            new FixedDepositAccount("Neha", 103, 50000)
        };
        for (Account a : accounts) 
            {
            System.out.println("Account Holder: " + a.name);
            System.out.println("Interest Rate: " + a.interestRate() + "%");
            if (a instanceof FixedDepositAccount) 
            {
                System.out.println("Note: Fixed Deposit is locked.");
            }
            System.out.println("Can withdraw ₹15000: " + a.canWithdraw(15000));
            System.out.println();
        }
    }
}
