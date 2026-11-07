import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService service = new BankService();

        while (true) {
            System.out.println("\n1. Add Account");
            System.out.println("2. Show Account Count");
            System.out.println("3. Deposit");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Account number: ");
                        String number = sc.nextLine();

                        System.out.print("Initial balance: ");
                        double balance =
                                Double.parseDouble(sc.nextLine());

                        if (balance < 0) {
                            throw new IllegalArgumentException(
                                    "Invalid balance");
                        }

                        service.addAccount(
                                new BankAccount(number, balance));
                        System.out.println("Account added");
                        break;

                    case 2:
                        System.out.println("Account count: "
                                + service.getAccountCount());
                        break;

                    case 3:
                        System.out.print("Account number: ");
                        String account = sc.nextLine();

                        System.out.print("Amount: ");
                        double amount =
                                Double.parseDouble(sc.nextLine());

                        service.deposit(account, amount);
                        System.out.println("Deposit successful");
                        break;

                    case 4:
                        System.out.println("Thank you");
                        sc.close();
                        return;

                    default:
                        throw new IllegalArgumentException(
                                "Invalid choice");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}