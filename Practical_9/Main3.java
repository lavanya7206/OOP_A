class Account {
    private long balance = 0;

    synchronized void deposit(long amount) {
        balance += amount;
    }

    synchronized long getBalance() {
        return balance;
    }
}
class AccountWorker implements Runnable {
    Account account;
    int times;
    long amount;
    AccountWorker(Account account, int times, long amount) {
        this.account = account;
        this.times = times;
        this.amount = amount;
    }
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started");
        for (int i = 0; i < times; i++) {
            account.deposit(amount);
        }
        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
public class Main3 {
    public static void main(String[] args) throws Exception {
        Account account = new Account();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(
                new AccountWorker(account, 1000, 10),
                "Worker-" + (i + 1)
            );
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Final Balance = " + account.getBalance());
    }
}