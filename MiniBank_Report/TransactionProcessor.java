import java.util.*;
import java.util.concurrent.*;
public class TransactionProcessor 
{
    private ExecutorService workers =Executors.newFixedThreadPool(4);
    public void processBatch(List<Runnable> transactions) 
    {
        List<Future<?>> jobs = new ArrayList<>();
        for (Runnable task : transactions) 
        {
            jobs.add(workers.submit(task));
        }
        for (Future<?> job : jobs) 
            {
            try 
            {
                job.get();
            } 
            catch (InterruptedException e) 
            {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Batch interrupted.", e);
            } 
            catch (ExecutionException e) 
            {
                throw new RuntimeException("A transaction failed.", e.getCause());
            }
        }
        System.out.println("Batch completed.");
    }
    public void stop() 
    {
        workers.shutdown();
    }
}