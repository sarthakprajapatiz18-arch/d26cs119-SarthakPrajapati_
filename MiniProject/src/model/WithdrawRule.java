package model;
// Purpose: Functional interface for custom withdrawal-approval rules.
@FunctionalInterface
public interface WithdrawRule {
    boolean allow(Account account, long amount);
}
