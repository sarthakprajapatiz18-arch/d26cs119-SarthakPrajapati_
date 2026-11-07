package util;

import model.Command;
import model.TransactionType;

/** Converts a simple three-part transaction command into a Command record. */
public class CommandParser {

    /**
     * Expected format: TYPE ACCOUNT_NUMBER AMOUNT.
     * Example: DEPOSIT AC0001 500
     */
    public static Command parse(String line) {
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("Command cannot be empty");
        }

        // A regular-expression whitespace token separates the three fields.
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Use: TYPE ACCOUNT_NUMBER AMOUNT");
        }

        try {
            TransactionType type = TransactionType.valueOf(parts[0].toUpperCase());
            long amount = Long.parseLong(parts[2]);
            return new Command(type, parts[1], amount);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid transaction command: " + e.getMessage(), e);
        }
    }
}
