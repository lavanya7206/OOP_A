import exception.*;

class BankResource implements AutoCloseable {
    public void open() {
        System.out.println("Bank resource opened.");
    }

    public void close() {
        System.out.println("Bank resource closed.");
    }
}

public class BankDemo {
    public static void main(String[] args) {

        Account a1 = new Account("101", "Lavanya", 5000);
        Account a2 = new Account("102", "Rahul", 2000);

        try {
            a1.deposit(1000);
            System.out.println("Deposit successful.");

            a1.withdraw(2000);
            System.out.println("Withdrawal successful.");

            a1.transfer(a2, 1500);

            System.out.println("Lavanya balance: " + a1.getBalance());
            System.out.println("Rahul balance: " + a2.getBalance());

        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Shortfall: " + e.getShortfall());

        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (BankException e) {
            System.out.println("Bank error: " + e.getMessage());

        } finally {
            System.out.println("Bank operation finished.");
        }
        try (BankResource resource = new BankResource()) {
            resource.open();
            System.out.println("Performing banking operation...");
        }
    }
}