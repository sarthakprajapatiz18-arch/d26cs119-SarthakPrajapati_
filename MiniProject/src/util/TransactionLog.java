package util;
// Purpose: Appends transaction records to a UTF-8 text file.
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
public class TransactionLog {
    public static void append(Path file, String line) throws IOException {
        Files.write(file, (line + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
