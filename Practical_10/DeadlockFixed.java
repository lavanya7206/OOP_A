class Account {
    int accountNumber;
    double balance;
    Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    void transfer(Account other, double amount) {
        Account first;
        Account second;
        if (this.accountNumber < other.accountNumber) {
            first = this;
            second = other;
        } else {
            first = other;
            second = this;
        }
        synchronized (first) {
            synchronized (second) {
                this.balance = this.balance - amount;
                other.balance = other.balance + amount;
                System.out.println("Transfer completed from Account "+ this.accountNumber + " to Account "+ other.accountNumber);
            }
        }
    }
}
public class DeadlockFixed {
    public static void main(String[] args) {
        Account a = new Account(101, 5000);
        Account b = new Account(102, 5000);
        Thread t1 = new Thread(() -> {
            a.transfer(b, 500);
        });
        Thread t2 = new Thread(() -> {
            b.transfer(a, 300);
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("Both transfers completed.");
    }
}