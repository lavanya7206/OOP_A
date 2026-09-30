import java.util.concurrent.atomic.AtomicLong;
public class Main2 {
    static int[] arr = new int[100000];
    static long total = 0;
    static AtomicLong atomicTotal = new AtomicLong(0);
    static class Worker extends Thread {
        int start, end;
        Worker(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public void run() {
            for (int i = start; i < end; i++)
                total += arr[i];
        }
    }
    static class SafeWorker extends Thread {
        int start, end;
        SafeWorker(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public void run() {
            long sum = 0;
            for (int i = start; i < end; i++)
                sum += arr[i];
            synchronized (Main.class) {
                total += sum;
            }
            atomicTotal.addAndGet(sum);
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < arr.length; i++)
            arr[i] = 1;
        total = 0;
        Worker[] t = new Worker[4];
        for (int i = 0; i < 4; i++) {
            t[i] = new Worker(i * 25000, (i + 1) * 25000);
            t[i].start();
        }
        for (Thread x : t)
            x.join();
        System.out.println("Without synchronization: " + total);
        total = 0;
        atomicTotal.set(0);
        long start = System.nanoTime();
        SafeWorker[] s = new SafeWorker[4];
        for (int i = 0; i < 4; i++) {
            s[i] = new SafeWorker(i * 25000, (i + 1) * 25000);
            s[i].start();
        }
        for (Thread x : s)
            x.join();
        long end = System.nanoTime();
        System.out.println("Synchronized: " + total);
        System.out.println("AtomicLong: " + atomicTotal);
        System.out.println("Time: " + (end - start) + " ns");
    }
}