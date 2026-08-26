public class Account 
{
    private int accountNumber;
    private String ownerName;
    private double balance;
    public Account(int accountNumber, String ownerName, double balance) 
{
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }
    public String toString() 
{
        return "Account No: " + accountNumber +
               ", Owner: " + ownerName +
               ", Balance: " + balance;
    }
    public boolean equals(Object obj) 
{
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Account a = (Account) obj;
        return accountNumber == a.accountNumber;
    }
    public int hashCode() 
{
        return Integer.hashCode(accountNumber);
    }
}
