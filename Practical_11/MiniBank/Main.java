import java.nio.file.*;
public class Main
{
    public static void main(String[] args)
    {
        try 
        {
            Path stateFile = Paths.get("accounts.dat");
            Path logsFolder = Paths.get("logs");
            Path logFile = logsFolder.resolve("transactions.txt");
            Path reportFile = Paths.get("report.txt");
            Files.createDirectories(logsFolder);
            Customer c1 = new Customer("Lavanya", 101);
            Customer c2 = new Customer("Riya", 102);
            Account[] accounts = {
                new SavingsAccount(1001, c1, 5000),
                new SavingsAccount(1002, c2, 8000)
            };
            StatePersister.save(accounts, stateFile);
            System.out.println("Accounts saved.");
            TransactionLog.append(logFile, "DEPOSIT 1000");
            TransactionLog.append(logFile, "WITHDRAW 500");
            TransactionLog.append(logFile, "DEPOSIT 2000");
            TransactionLog.append(logFile, "WITHDRAW 300");
            Account[] loadedAccounts =StatePersister.load(stateFile);
            System.out.println("\nLoaded Accounts:");
            for (Account a : loadedAccounts) 
            {
                System.out.println(a);
            }
            ReportGenerator.generate(logsFolder, reportFile);
            System.out.println("\nEnd-of-day Report:");
            System.out.println(new String(Files.readAllBytes(reportFile)));
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
