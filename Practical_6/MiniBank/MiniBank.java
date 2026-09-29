import model.Account;
import service.WithdrawRule;

import static util.BankUtil.*;

public class MiniBank {

    public static void main(String[] args) {

        Account account = new Account(
                "A101",
                "Lavanya",
                10000
        );

        printAccount(account);

        WithdrawRule rule1 = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return amount <= account.balance();
            }
        };

        System.out.println(
                "Anonymous class: " +
                rule1.allow(account, 2000)
        );
        WithdrawRule rule2 =
                (acc, amount) -> amount <= acc.balance();

        System.out.println(
                "Lambda: " +
                rule2.allow(account, 3000)
        );

        account.withdraw(2000);

        printAccount(account);
        printInterest(account);

        if (account instanceof model.Premium) {
            System.out.println("Premium account.");
        }
    }
}
