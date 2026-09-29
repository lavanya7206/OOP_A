package util;

import model.Account;

public class BankUtil {

    public static void printAccount(Account account) {
        System.out.println("Account: " + account);
    }

    public static void printInterest(Account account) {
        System.out.println("Yearly Interest: " +
                account.yearlyInterest());
    }
}