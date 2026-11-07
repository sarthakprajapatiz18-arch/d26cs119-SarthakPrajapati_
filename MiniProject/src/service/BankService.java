package service;

import exception.*;
import model.*;
import util.*;
import java.io.IOException;
import java.nio.file.*;

/** Coordinates account operations, persistence, transaction logging and reports. */
public class BankService {
    private final Repository<Account> repository = new Repository<>();
    private final Path logFile = Path.of("logs", "transactions.csv");

    /** Creates and stores an account of the selected type. */
    public Account openAccount(String name, int type, long opening) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Owner name cannot be empty");
        }
        if (opening < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }

        // Do not silently treat an unknown account type as a savings account.
        Account account = switch (type) {
            case 1 -> new SavingsAccount(name, opening, 0);
            case 2 -> new CurrentAccount(name, opening, 1000);
            case 3 -> new FixedDepositAccount(name, opening);
            default -> throw new IllegalArgumentException("Select account type 1, 2 or 3");
        };

        repository.save(account);
        log("OPEN," + opening + "," + account.getAccountNumber());
        return account;
    }

    /** Deposits money into the selected account and records the transaction. */
    public void deposit(String id, long amount) throws BankException {
        Account account = require(id);
        account.deposit(amount);
        log("DEPOSIT," + amount + "," + id);
    }

    /** Withdraws money from the selected account and records the transaction. */
    public void withdraw(String id, long amount) throws BankException {
        Account account = require(id);
        account.withdraw(amount);
        log("WITHDRAW," + amount + "," + id);
    }

    /**
     * Transfers money between two accounts.
     * If crediting the destination fails, the source withdrawal is rolled back.
     */
    public synchronized void transfer(String from, String to, long amount) throws BankException {
        if (amount <= 0) {
            throw new InvalidAmountException("Transfer amount must be positive");
        }

        Account source = require(from);
        Account destination = require(to);

        // A transfer to the same account has no financial effect.
        if (source.equals(destination)) {
            return;
        }

        source.withdraw(amount);
        try {
            destination.deposit(amount);
            log("TRANSFER," + amount + "," + from + "," + to);
        } catch (BankException | RuntimeException e) {
            // Restore the source balance so a failed transfer cannot lose money.
            source.deposit(amount);
            throw e;
        } finally {
            System.out.println("Transfer attempt finished.");
        }
    }

    /** Builds a formatted statement for the requested account. */
    public String statement(String id) throws BankException {
        return StatementFormatter.buildStatement(require(id));
    }

    /** Prints all accounts currently held in this application session. */
    public void listAccounts() {
        for (Account account : repository.findAll()) {
            System.out.println(account);
        }
    }

    /** Looks up an account or throws a checked account-not-found exception. */
    public Account require(String id) throws AccountNotFoundException {
        Account account = repository.findById(id);
        if (account == null) {
            throw new AccountNotFoundException("Account not found: " + id);
        }
        return account;
    }

    /** Saves the current account collection to a serialized data file. */
    public void saveState() {
        try {
            Files.createDirectories(Path.of("data"));
            StatePersister.save(
                repository.findAll().toArray(new Account[0]),
                Path.of("data/accounts.bin")
            );
            System.out.println("Account state saved.");
        } catch (IOException e) {
            System.out.println("Could not save state: " + e.getMessage());
        }
    }

    /** Appends a transaction row to the CSV log. */
    private void log(String line) {
        try {
            Files.createDirectories(logFile.getParent());
            TransactionLog.append(logFile, line);
        } catch (IOException e) {
            System.out.println("Log warning: " + e.getMessage());
        }
    }

    /** Generates the end-of-day report from the transaction log. */
    public void report() {
        try {
            ReportGenerator.generate(Path.of("logs"), Path.of("report.txt"));
            System.out.println("Report written to report.txt");
        } catch (IOException e) {
            System.out.println("Could not generate report: " + e.getMessage());
        }
    }
}
