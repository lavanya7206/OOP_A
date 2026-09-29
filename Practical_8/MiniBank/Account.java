import exception.*;

public class Account {
    private String accountNo;
    private String name;
    private long balance;

    public Account(String accountNo, String name, long balance) {
        this.accountNo = accountNo;
        this.name = name;
        this.balance = balance;
    }

    public void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }

        balance += amount;
    }

    public void withdraw(long amount)
            throws InsufficientFundsException, InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }

        if (amount > balance) {
            long shortfall = amount - balance;
            throw new InsufficientFundsException(
                    "Insufficient funds. Short by " + shortfall,
                    shortfall);
        }

        balance -= amount;
    }

    public void transfer(Account to, long amount) throws BankException {
        try {
            withdraw(amount);
            to.deposit(amount);
            System.out.println("Transfer successful.");
        } catch (BankException e) {
            throw e;
        } finally {
            System.out.println("Transfer attempt completed.");
        }
    }

    public long getBalance() {
        return balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getName() {
        return name;
    }
}
