package model;

import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import model.annotation.Id;
import model.annotation.Positive;
import java.io.Serializable;
import java.util.Objects;

/**
 * Base class for all MiniBank account types.
 * Stores shared identity, owner, balance and synchronized transaction behavior.
 */
public abstract class Account implements Transactable, InterestBearing, Comparable<Account>, Serializable {
    private static final long serialVersionUID = 1L;
    private static long counter = 1;
    @Id
    private final String accountNumber;
    private String ownerName;
    @Positive(message = "Balance must be >= 0")
    private long balance;
    private boolean active = true;

    protected Account(String ownerName, long openingBalance) {
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name cannot be empty");
        }
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }

        // Generate a unique account number and store validated account details.
        this.accountNumber = String.format("AC%04d", counter++);
        this.ownerName = ownerName.trim();
        this.balance = openingBalance;
    }
    @Override public synchronized void deposit(long amount) throws BankException {
        if (!active) throw new BankException("Account is inactive");
        if (amount <= 0) throw new InvalidAmountException("Deposit amount must be positive");
        try {
            // addExact prevents a silent overflow of the account balance.
            balance = Math.addExact(balance, amount);
        } catch (ArithmeticException e) {
            throw new InvalidAmountException("Deposit exceeds the maximum supported balance");
        }
    }
    @Override public synchronized boolean withdraw(long amount) throws BankException {
        if (!active) throw new BankException("Account is inactive");
        if (amount <= 0) throw new InvalidAmountException("Withdrawal amount must be positive");
        if (!canWithdraw(amount)) throw new InsufficientFundsException("Insufficient funds", Math.max(0, amount - balance));
        balance -= amount;
        return true;
    }
    public abstract boolean canWithdraw(long amount);
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    @Override public int compareTo(Account other) { return accountNumber.compareTo(other.accountNumber); }
    @Override public String toString() { return accountNumber + " | " + ownerName + " | Balance: ₹" + balance; }
    @Override public boolean equals(Object o) { return this == o || (o instanceof Account a && accountNumber.equals(a.accountNumber)); }
    @Override public int hashCode() { return Objects.hash(accountNumber); }
}