import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {
        List<String> visitors = Arrays.asList(
            "Amit", "Neha", "Rahul", "Amit",
            "Priya", "Neha", "Karan"
        );

        Set<String> unique = new HashSet<>(visitors);
        LinkedHashSet<String> ordered =
                new LinkedHashSet<>(visitors);

        System.out.println("Unique visitors: " + unique.size());

        System.out.println("First three distinct visitors:");

        int count = 0;

        for (String name : ordered) {
            if (count == 3) {
                break;
            }

            System.out.println(name);
            count++;
        }
    }
}    
