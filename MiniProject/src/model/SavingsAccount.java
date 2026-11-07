package model;
// Purpose: Savings account with a minimum-balance withdrawal rule.
public class SavingsAccount extends Account {
    private static final long serialVersionUID = 1L;
    private long minBalance;
    // Store the minimum balance that must remain after a withdrawal.
    public SavingsAccount(String owner, long opening, long minBalance) { super(owner, opening); this.minBalance = minBalance; }
    @Override public double interestRate() { return 4.0; }
    @Override public boolean canWithdraw(long amount) { return getBalance() - amount >= minBalance; }
}
