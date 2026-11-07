import java.util.Scanner;
import model.*;
import service.BankService;
import exception.BankException;

/** Console entry point for the MiniBank account-management application. */
public class MiniBank {

    /** Displays the menu and routes each user selection to the bank service. */
    public static void main(String[] args) {
        // Scanner reads all console input; BankService owns the business operations.
        try (Scanner scanner = new Scanner(System.in)) {
            BankService bank = new BankService();
            BankInfo info = new BankInfo("MiniBank", "Anand");

            System.out.println("================================");
            System.out.println("       " + info.name());
            System.out.println("       Branch: " + info.branch());
            System.out.println("================================");

            boolean running = true;
            while (running) {
                // Show all supported banking operations.
                System.out.println(
                    "\n1. Open Account\n2. Deposit\n3. Withdraw\n4. Transfer"
                    + "\n5. Statement\n6. List Accounts\n7. Report\n8. Exit"
                );
                System.out.print("Choice: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a number.");
                    scanner.nextLine(); // Discard invalid input and show the menu again.
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline left after nextInt().

                try {
                    switch (choice) {
                        case 1 -> {
                            System.out.print("Owner name: ");
                            String name = scanner.nextLine();

                            System.out.print("Opening balance: ");
                            long opening = Long.parseLong(scanner.nextLine());

                            System.out.print("Type (1 Savings, 2 Current, 3 Fixed Deposit): ");
                            int type = Integer.parseInt(scanner.nextLine());

                            Account account = bank.openAccount(name, type, opening);
                            System.out.println("Account created: " + account);
                        }
                        case 2 -> {
                            System.out.print("Account number: ");
                            String id = scanner.nextLine();
                            System.out.print("Amount: ");
                            long amount = Long.parseLong(scanner.nextLine());
                            bank.deposit(id, amount);
                            System.out.println("Deposit successful.");
                        }
                        case 3 -> {
                            System.out.print("Account number: ");
                            String id = scanner.nextLine();
                            System.out.print("Amount: ");
                            long amount = Long.parseLong(scanner.nextLine());
                            bank.withdraw(id, amount);
                            System.out.println("Withdrawal successful.");
                        }
                        case 4 -> {
                            System.out.print("From account: ");
                            String from = scanner.nextLine();
                            System.out.print("To account: ");
                            String to = scanner.nextLine();
                            System.out.print("Amount: ");
                            long amount = Long.parseLong(scanner.nextLine());
                            bank.transfer(from, to, amount);
                            System.out.println("Transfer successful.");
                        }
                        case 5 -> {
                            System.out.print("Account number: ");
                            System.out.println(bank.statement(scanner.nextLine()));
                        }
                        case 6 -> bank.listAccounts();
                        case 7 -> bank.report();
                        case 8 -> {
                            bank.saveState();
                            running = false;
                            System.out.println("Goodbye! Thank you for using MiniBank.");
                        }
                        default -> System.out.println("Invalid choice. Select 1 to 8.");
                    }
                } catch (BankException | IllegalArgumentException e) {
                    // Display expected validation and banking errors without terminating the app.
                    System.out.println("Operation failed: " + e.getMessage());
                }
            }
        }
    }
}
