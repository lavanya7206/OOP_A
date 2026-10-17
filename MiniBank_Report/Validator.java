public class Validator {
    public void checkAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException( "Amount must be greater than zero.");
        }
    }
    public void checkAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
    }
}