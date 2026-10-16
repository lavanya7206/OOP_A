public class Main 
{
    public static void main(String[] args)
    {
        AccountStore store = new AccountStore();
        store.add(new Account("A103", "Amit", 5000));
        store.add(new Account("A101", "Riya", 9000));
        store.add(new Account("A102", "Rahul", 7000));
        store.addTransaction("Amit deposited 500");
        store.addTransaction("Riya deposited 1000");
        System.out.println("Accounts sorted by balance:");
        for (Account account : store.sortedByBalance()) 
        {
            System.out.println(account);
        }
        System.out.println("\nAccount search:");
        System.out.println(store.get("A102"));
        System.out.println("\nTransaction History:");
        for (String transaction : store.getTransactionHistory()) 
        {
            System.out.println(transaction);
        }
        TransactionProcessor processor = new TransactionProcessor();
        for (Account account : store.getAll()) 
        {
            processor.add(account);
        }
        System.out.println("\nThread-safe account search:");
        System.out.println(processor.get("A101"));
    }
}