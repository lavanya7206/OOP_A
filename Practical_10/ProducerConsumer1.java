class Buffer {
    int[] buffer = new int[3];
    int count = 0;
    synchronized void produce(int value) {
        while (count == buffer.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        buffer[count] = value;
        count++;
        System.out.println("Produced: " + value);
        notify();
    }
    synchronized void consume() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        int value = buffer[0];
        for (int i = 0; i < count - 1; i++) {
            buffer[i] = buffer[i + 1];
        }
        count--;
        System.out.println("Consumed: " + value);
        notify();
    }
}
public class ProducerConsumer1 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                buffer.produce(i);
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                buffer.consume();
            }
        });
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("All transactions processed.");
    }
}