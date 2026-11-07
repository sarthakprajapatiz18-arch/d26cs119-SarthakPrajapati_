
import model.*;
import service.*;
import util.*;
import exception.*;
import java.nio.file.*;

public class MiniBankRegressionTest {
    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) throws Exception {
        // Inherited @Id lookup and ordinary account transactions.
        Repository<Account> repository = new Repository<>();
        Account savings = new SavingsAccount("Test User", 5000, 0);
        repository.save(savings);
        check(repository.findById(savings.getAccountNumber()) == savings,
              "Repository must find inherited @Id");

        savings.deposit(500);
        savings.withdraw(300);
        check(savings.getBalance() == 5200, "Deposit/withdraw balance incorrect");

        // Command parser must split on one or more whitespace characters.
        Command command = CommandParser.parse("DEPOSIT   AC0001   250");
        check(command.type() == TransactionType.DEPOSIT && command.amount() == 250,
              "Command parser failed whitespace parsing");

        // Annotation validation must inspect superclass fields.
        check(AnnotationValidator.validate(savings).length == 0,
              "Valid account should pass inherited annotation validation");

        // Current account overdraft allowance is applied.
        Account current = new CurrentAccount("Current User", 100, 1000);
        current.withdraw(500);
        check(current.getBalance() == -400, "Current account overdraft failed");

        // Fixed deposit blocks withdrawal.
        Account fixed = new FixedDepositAccount("FD User", 1000);
        boolean fdBlocked = false;
        try { fixed.withdraw(1); } catch (InsufficientFundsException expected) { fdBlocked = true; }
        check(fdBlocked, "Fixed deposit should reject withdrawal");

        // A failed destination credit must roll back the source debit.
        BankService bank = new BankService();
        Account source = bank.openAccount("Source", 1, 1000);
        Account full = bank.openAccount("Full", 1, Long.MAX_VALUE);
        boolean transferFailed = false;
        try { bank.transfer(source.getAccountNumber(), full.getAccountNumber(), 100); }
        catch (InvalidAmountException expected) { transferFailed = true; }
        check(transferFailed, "Overflowing destination credit should fail");
        check(source.getBalance() == 1000, "Failed transfer did not restore source balance");
        check(full.getBalance() == Long.MAX_VALUE, "Failed transfer changed destination balance");

        // Serialization includes account objects and nested customer address values.
        Path temp = Path.of("test-accounts.bin");
        StatePersister.save(new Account[] { savings, current }, temp);
        Account[] loaded = StatePersister.load(temp);
        check(loaded.length == 2, "State persistence round-trip failed");
        Files.deleteIfExists(temp);

        Customer customer = new Customer("A", "a@example.com", "9876543210");
        customer.setAddress(new Customer.Address("Street", "Anand", "388001"));
        Path customerFile = Path.of("test-customer.bin");
        try (java.io.ObjectOutputStream out =
                 new java.io.ObjectOutputStream(Files.newOutputStream(customerFile))) {
            out.writeObject(customer);
        }
        Files.deleteIfExists(customerFile);

        System.out.println("ALL REGRESSION TESTS PASSED");
    }
}
