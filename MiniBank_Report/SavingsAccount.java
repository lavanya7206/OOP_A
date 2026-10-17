public class SavingsAccount extends Account 
{
    private static final long serialVersionUID = 1L;
    private double minimumBalance = 500;
    public SavingsAccount(String number, String holder, double balance) 
    {
        super(number, holder, balance);
    }
    public double getMinimumBalance() 
    {
        return minimumBalance;
    }
}