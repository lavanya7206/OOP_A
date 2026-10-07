class Buffer 
{
    int item;
    boolean available = false;
    synchronized void produce(int value) 
    {
        try {
            while (available) 
            {
                wait();
            }
            item = value;
            available = true;
            System.out.println("Produced: " + item);
            notify();
        } 
        catch (InterruptedException e) 
        {
            System.out.println("Producer interrupted");
        }
    }
    synchronized void consume() 
    {
        try 
        {
            while (!available) 
            {
                wait();
            }
            System.out.println("Consumed: " + item);
            available = false;
            notify();
        }
        catch (InterruptedException e) 
        {
            System.out.println("Consumer interrupted");
        }
    }
}
public class ProducerConsumer 
{
    public static void main(String[] args) 
    {
        Buffer buffer = new Buffer();
        Thread producer = new Thread(() -> 
        {
            for (int i = 1; i <= 5; i++)
            {
                buffer.produce(i);
            }
        });
        Thread consumer = new Thread(() -> 
        {
            for (int i = 1; i <= 5; i++) 
            {
                buffer.consume();
            }
        });
        producer.start();
        consumer.start();
        try
        {
            producer.join();
            consumer.join();
        }
        catch (InterruptedException e) 
        {
            System.out.println("Main thread interrupted");
        }
        System.out.println("All items produced and consumed.");
    }
}
