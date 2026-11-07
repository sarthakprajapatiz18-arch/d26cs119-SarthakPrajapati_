package model;
// Purpose: Contract for account types that provide an interest rate.
public interface InterestBearing {
    double interestRate();
    default double yearlyInterest() { return getBalance() * interestRate() / 100.0; }
    long getBalance();
}
