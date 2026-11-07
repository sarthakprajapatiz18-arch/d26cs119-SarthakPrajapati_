import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class LogAnalyzer {
    public static void main(String[] args) throws IOException {
        Path file1 = Path.of("log1.txt");
        Path file2 = Path.of("log2.txt");

        Files.write(file1, List.of(
            "INFO Application started",
            "ERROR Database failed",
            "INFO Retry successful"
        ));

        Files.write(file2, List.of(
            "WARN Low memory",
            "ERROR Connection failed"
        ));

        List<Path> files = List.of(file1, file2);
        int totalLines = 0;
        int matches = 0;
        String keyword = "ERROR";

        for (Path file : files) {
            long size = Files.size(file);
            int fileLines = 0;

            for (String line : Files.readAllLines(file)) {
                totalLines++;
                fileLines++;

                if (line.contains(keyword)) {
                    matches++;
                }
            }

            System.out.println(file.getFileName()
                    + " size: " + size + " bytes");
            System.out.println("Lines: " + fileLines);
        }

        System.out.println("Total lines: " + totalLines);
        System.out.println("ERROR lines: " + matches);
    }
}
    
