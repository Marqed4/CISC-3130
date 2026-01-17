import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeworkMethods {

    public static void main(String[] args) {
        IO.println(frequencyCount(List.of("ab", "ab", "a", "ab", "a", "b", "a", "a"), 3)); //1
        IO.println(frequencyCount(List.of("a", "c", "b", "a", "a"), 1)); //2
        IO.println(maxFrequency(List.of("a", "c", "b", "a", "a"))); //3
    }

    public static int frequencyCount(Collection<String> collection, int frequency) {

        // TODO
        //Record frequencies in a map.
        //Return total elements with a specific frequency.

        HashMap<String, Integer> map = new HashMap<>();
        int total = 0;

        for (String s : collection) {
            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == frequency) {
                total++;
            }
        }
        return total;
    }

    public static int maxFrequency(Collection<String> collection) {

        // TODO
        //Record Frequencies in a map
        //Return largest frequencies

        HashMap<String, Integer> map = new HashMap<>();
        int max = 0;

        for (String s : collection) {
            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }

        return max;
    }
}