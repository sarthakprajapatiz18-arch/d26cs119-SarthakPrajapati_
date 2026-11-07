package model;
// Purpose: Contract for depositing and withdrawing money from an account.
import exception.BankException;
public interface Transactable {
    void deposit(long amount) throws BankException;
    boolean withdraw(long amount) throws BankException;
}
