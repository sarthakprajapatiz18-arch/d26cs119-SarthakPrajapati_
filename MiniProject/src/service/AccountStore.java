package service;
// Purpose: Concurrent account store with history and balance-sorting helpers.
import model.Account;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
public class AccountStore {
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final ArrayList<String> history = new ArrayList<>();
    public void add(Account account) { accounts.put(account.getAccountNumber(), account); }
    public Account get(String number) { return accounts.get(number); }
    public ArrayList<Account> getAll() { return new ArrayList<>(accounts.values()); }
    public ArrayList<String> getHistory() { return history; }
    public void record(String line) { history.add(line); }
    public ArrayList<Account> sortedByBalance() {
        ArrayList<Account> result = getAll();
        result.sort(Comparator.comparingLong(Account::getBalance));
        return result;
    }
}
