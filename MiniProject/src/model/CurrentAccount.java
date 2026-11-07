package model;
// Purpose: Current account implementation with a configurable overdraft limit.
public class CurrentAccount extends Account {
    private static final long serialVersionUID = 1L;
    private long overdraftLimit;
    // Configure the account's overdraft allowance at creation.
    public CurrentAccount(String owner, long opening, long overdraftLimit) { super(owner, opening); this.overdraftLimit = overdraftLimit; }
    @Override public double interestRate() { return 0.0; }
    @Override public boolean canWithdraw(long amount) { return getBalance() - amount >= -overdraftLimit; }
}
