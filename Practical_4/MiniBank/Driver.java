public class Driver {
    public static void main(String[] args) {
        System.out.println("Mobile:");
        System.out.println(Validator.isValidMobile("9876543210"));
        System.out.println(Validator.isValidMobile("98765"));
        System.out.println("\nEmail:");
        System.out.println(Validator.isValidEmail("riya@gmail.com"));
        System.out.println(Validator.isValidEmail("riyagmail.com"));
        System.out.println("\nPAN:");
        System.out.println(Validator.isValidPan("ABCDE1234F"));
        System.out.println(Validator.isValidPan("ABC123"));
        System.out.println("\nIFSC:");
        System.out.println(Validator.isValidIfsc("SBIN0001234"));
        System.out.println(Validator.isValidIfsc("SBIN123"));
        System.out.println("\nCommand:");
        Command command = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println("Type: " + command.type());
        System.out.println("Account: " + command.accountNumber());
        System.out.println("Amount: " + command.amount());
    }
}
