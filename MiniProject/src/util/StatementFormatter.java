package util;
// Purpose: Builds a readable account statement from account details.
import model.Account;
public class StatementFormatter {
    public static String buildStatement(Account account) {
        StringBuilder sb = new StringBuilder();
        sb.append("------ ACCOUNT STATEMENT ------\n");
        sb.append("Account: ").append(account.getAccountNumber()).append('\n');
        sb.append("Owner: ").append(account.getOwnerName()).append('\n');
        sb.append("Balance: ₹").append(account.getBalance()).append('\n');
        sb.append("Interest rate: ").append(account.interestRate()).append("%\n");
        sb.append("-------------------------------");
        return sb.toString();
    }
}
