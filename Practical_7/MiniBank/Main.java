public class Main {
    public static void main(String[] args) {

        Account account = new Account(
                "A101",
                "Lavanya",
                -500
        );

        String[] errors = AnnotationValidator.validate(account);

        if (errors.length == 0) {
            System.out.println("Account is valid.");
        } else {
            System.out.println("Validation Errors:");

            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}