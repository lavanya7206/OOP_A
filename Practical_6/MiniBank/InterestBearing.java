package model;

public interface InterestBearing {

    double interestRate();

    default double yearlyInterest() {
        return balance() * interestRate();
    }

    double balance();
}