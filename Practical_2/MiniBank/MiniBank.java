public class MiniBank {
    public static void main(String[] args) {
        Customer c1 = new Customer("Lavanya", "lavanya@gmail.com", "9876543210");
        System.out.println("Customer Details");
        System.out.println("Customer ID: " + c1.getCustomerId());
        System.out.println("Name: " + c1.getName());
        System.out.println("Email: " + c1.getEmail());
        System.out.println("Mobile: " + c1.getMobile());
        System.out.println();
        Account[] accounts = new Account[3];
        accounts[0] = new Account("Lavanya", 5000);
        accounts[1] = new Account("Rahul", 3000);
        accounts[2] = new Account("Priya");
        accounts[0].deposit(1000);
        accounts[0].withdraw(2000);
        accounts[1].deposit(500);
        accounts[1].withdraw(1000);
        accounts[2].deposit(2000);
        accounts[2].withdraw(500);
        System.out.println("\nAccount Details");
        for (int i = 0; i < accounts.length; i++) {
            System.out.println("Account Number : " + accounts[i].getAccountNumber());
            System.out.println("Owner Name     : " + accounts[i].getOwnerName());
            System.out.println("Balance        : " + accounts[i].getBalance());
            System.out.println();
        }
    }
}
