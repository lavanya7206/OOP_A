import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class TransactionProcessor {
    ExecutorService pool = Executors.newFixedThreadPool(4);
    void submit(Runnable task) {
        pool.execute(task);
    }
    void stop() {
        pool.shutdown();
        try {
            pool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
    public static void main(String[] args) {
        TransactionProcessor processor = new TransactionProcessor();
        for (int i = 1; i <= 8; i++) {
            int id = i;
            processor.submit(() -> {
                if (id % 2 == 0) {
                    System.out.println("Withdrawal transaction: " + id);
                } else {
                    System.out.println("Deposit transaction: " + id);
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            });
        }
        processor.stop();
        System.out.println("All transactions completed.");
    }
}