package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/** Creates a summary report from the MiniBank transaction CSV file. */
public class ReportGenerator {

    /** Reads transaction records and writes deposit/withdrawal totals to a report. */
    public static void generate(Path folder, Path report) throws IOException {
        long deposits = 0;
        long withdrawals = 0;
        Files.createDirectories(folder);

        // Read only the transaction CSV; do not parse unrelated files in the log folder.
        Path transactionFile = folder.resolve("transactions.csv");
        if (Files.exists(transactionFile)) {
            try (BufferedReader reader = Files.newBufferedReader(transactionFile)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length < 2) {
                        continue;
                    }

                    try {
                        long amount = Long.parseLong(parts[1]);
                        if ("DEPOSIT".equals(parts[0])) {
                            deposits = Math.addExact(deposits, amount);
                        } else if ("WITHDRAW".equals(parts[0])) {
                            withdrawals = Math.addExact(withdrawals, amount);
                        }
                    } catch (NumberFormatException ignored) {
                        // Ignore malformed rows instead of stopping report generation.
                    }
                }
            }

            // Include basic metadata for the transaction file in the console.
            BasicFileAttributes attributes =
                Files.readAttributes(transactionFile, BasicFileAttributes.class);
            System.out.println(transactionFile.getFileName()
                + " size=" + attributes.size()
                + " modified=" + attributes.lastModifiedTime());
        }

        // Write a fresh report so old totals are not accidentally retained.
        String reportText = "MiniBank End-of-Day Report\n"
            + "Total deposits: ₹" + deposits + "\n"
            + "Total withdrawals: ₹" + withdrawals + "\n";
        Files.writeString(report, reportText);
    }
}
