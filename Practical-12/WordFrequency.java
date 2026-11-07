import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        String paragraph =
            "java is easy and java is powerful";

        Map<String, Integer> frequency = new HashMap<>();

        for (String word : paragraph.toLowerCase().split("\\s+")) {
            frequency.put(word,
                    frequency.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entries =
                new ArrayList<>(frequency.entrySet());

        entries.sort(
            Comparator.comparingInt(
                (Map.Entry<String, Integer> e) -> e.getValue()
            ).reversed().thenComparing(Map.Entry::getKey)
        );

        System.out.println("Word frequencies:");

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey()
                    + ": " + entry.getValue());
        }
    }
}
