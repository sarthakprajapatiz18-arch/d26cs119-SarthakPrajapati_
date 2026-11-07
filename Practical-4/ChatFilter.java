import java.util.Scanner;

public class ChatFilter {
    public static void main(String[] args) {
        String[] logs = {
            "10:05 alice Hello there",
            "10:06 bob How are you",
            "10:07 charlie HELLO everyone",
            "Invalid line"
        };

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        int matches = 0;
        StringBuilder report = new StringBuilder();

        for (String log : logs) {
            String[] parts = log.split(" ", 3);

            if (parts.length < 3) {
                continue;
            }

            if (parts[2].toLowerCase()
                    .contains(keyword.toLowerCase())) {
                matches++;
                report.append(parts[0]).append(" ")
                      .append(parts[1]).append(": ")
                      .append(parts[2]).append("\n");
            }
        }

        System.out.println("Matches: " + matches);
        System.out.print(report);

        sc.close();
    }
}

