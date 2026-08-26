public class Driver 
{
    public static void main(String[] args) 
{
        Account a1 = new Account(101, "Lavanya", 5000);
        Account a2 = new Account(101, "Rahul", 7000);
        Account a3 = new Account(102, "Amit", 9000);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println();
        System.out.println("a1 equals a2 : " + a1.equals(a2));
        System.out.println("a1 equals a3 : " + a1.equals(a3));
        System.out.println();
        Customer.Address addr =
                new Customer.Address("ABC Road", "Ahmedabad", "380001");
        Customer c1 = new Customer("Lavanya", addr);
        Customer c2 = c1.clone();
        System.out.println("c1 is Customer : " + (c1 instanceof Customer));
        System.out.println("a1 is Account : " + (a1 instanceof Account));
        System.out.println();
        System.out.println("City : " + c1.getAddress().getCity());
        System.out.println("Pincode : " + c1.getAddress().getPincode());
    }
}
