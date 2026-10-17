import java.io.*;
import java.util.*;
public class Main 
{
    static final String FILE = "accounts.dat";
    public static void main(String[] args) 
    {
        AccountStore store = loadAccounts();
        BankService bank = new BankService(store, new Validator());
        Scanner sc = new Scanner(System.in);
        while (true) 
            {
            System.out.println("\n--- MiniBank ---");
            System.out.println("1. Open account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Statement");
            System.out.println("6. List accounts");
            System.out.println("7. Bank report");
            System.out.println("8. Batch deposits");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            try 
            {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) 
                {
                    case 1:
                        System.out.print("Account number: ");
                        String no = sc.nextLine();
                        System.out.print("Holder name: ");
                        String name = sc.nextLine();
                        System.out.print("Opening balance: ");
                        double opening =Double.parseDouble(sc.nextLine());
                        bank.openAccount(no, name, opening);
                        break;
                    case 2:
                        System.out.print("Account number: ");
                        no = sc.nextLine();
                        System.out.print("Amount: ");
                        double amount =Double.parseDouble(sc.nextLine());
                        bank.deposit(no, amount);
                        break;
                    case 3:
                        System.out.print("Account number: ");
                        no = sc.nextLine();
                        System.out.print("Amount: ");
                        amount = Double.parseDouble(sc.nextLine());
                        bank.withdraw(no, amount);
                        break;
                    case 4:
                        System.out.print("From account: ");
                        String from = sc.nextLine();
                        System.out.print("To account: ");
                        String to = sc.nextLine();
                        System.out.print("Amount: ");
                        amount = Double.parseDouble(sc.nextLine());
                        bank.transfer(from, to, amount);
                        break;
                    case 5:
                        bank.statement();
                        break;
                    case 6:
                        bank.listAccounts();
                        break;
                    case 7:
                        bank.printReport();
                        break;
                    case 8:
                        System.out.print("Account number: ");
                        no = sc.nextLine();
                        System.out.print("Amount per deposit: ");
                        amount = Double.parseDouble(sc.nextLine());
                        System.out.print("Number of deposits: ");
                        int count = Integer.parseInt(sc.nextLine());
                        if (count < 1 || count > 10000) 
                        {
                            System.out.println("Invalid deposit count.");
                            break;
                        }
                        List<Runnable> tasks = new ArrayList<>();
                        final String accountNo = no;
                        final double depositAmount = amount;
                        for (int i = 0; i < count; i++) 
                        {
                            tasks.add(() ->bank.deposit(accountNo, depositAmount));
                        }
                        TransactionProcessor processor =new TransactionProcessor();
                        try 
                        {
                            processor.processBatch(tasks);
                        } 
                        finally 
                        {
                            processor.stop();
                        }
                        break;
                    case 9:
                        saveAccounts(store);
                        System.out.println("Data saved. Goodbye!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid menu choice.");
                }
            }
            catch (RuntimeException e)
            {
    System.out.println("Error: " + e.getMessage());
}
        }
    }
    static AccountStore loadAccounts() 
    {
        File file = new File(FILE);
        if (!file.exists()) {
            return new AccountStore();
        }
        try (ObjectInputStream in =new ObjectInputStream(new FileInputStream(file))) {
            return (AccountStore) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load old data: "
                    + e.getMessage());
            return new AccountStore();
        }
    }
        static void saveAccounts(AccountStore store) {
        try (ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(store);
        } catch (IOException e) {
            System.out.println("Could not save data: "+ e.getMessage());
        }
    }
}