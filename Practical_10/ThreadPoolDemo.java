import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadPoolDemo 
{
    public static void main(String[] args) 
    {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) 
            {
            int id = i;
            pool.submit(new Runnable() 
            {
                public void run() 
                {
                    System.out.println("Task " + id + " is running on "+ Thread.currentThread().getName());
                    try 
                    {
                        Thread.sleep(1000);
                    } 
                    catch (InterruptedException e) 
                    {
                        System.out.println("Task interrupted");
                    }
                    System.out.println("Task " + id + " completed");
                }
            });
        }
        pool.shutdown();
        try
        {
            pool.awaitTermination(20, java.util.concurrent.TimeUnit.SECONDS);
        }
        catch (InterruptedException e) 
        {
            System.out.println("Main thread interrupted");
        }
        System.out.println("All tasks completed.");
    }
}
