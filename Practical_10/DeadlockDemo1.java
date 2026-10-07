class Account {
    int accountNumber;
    Account(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
public class DeadlockDemo1 {
    public static void main(String[] args) {
        Account accountA = new Account(101);
        Account accountB = new Account(102);
        Thread t1 = new Thread(() -> {
            synchronized (accountA) {
                System.out.println("Thread 1 locked Account 101");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                synchronized (accountB) {
                    System.out.println("Thread 1 locked Account 102");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (accountB) {
                System.out.println("Thread 2 locked Account 102");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                synchronized (accountA) {
                    System.out.println("Thread 2 locked Account 101");
                }
            }
        });
        t1.start();
        t2.start();
    }
}