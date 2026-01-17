import java.util.*;

//https://www.codestepbystep.com/r/problem/view/java/collections/map/CountNames
//Meets the problems expectations
class CountNames {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //uses linkedHashMap to print following order of original input
        LinkedHashMap<String, Integer> names = new LinkedHashMap<>();
        boolean on = true;

        while (on) {
            System.out.print("Enter name: ");
            String s = scanner.nextLine();

            if (s.isEmpty()) on = false;

            if (on) {
                if (!names.containsKey(s)) {
                    names.put(s, 1);
                } else {
                    int value = names.get(s) + 1;
                    names.put(s, value);
                }
            }
        }

        for (Map.Entry entry : names.entrySet()) {
            System.out.println("Entry [" + entry.getKey() + "] has count " + entry.getValue());
        }
    }
}

public class CodeStepByStepMethods {

    void main() {
//        countNames();

        //zipcodes
        List<Integer> list = new ArrayList<>(List.of(1, 3, 3, 4, 4));
        Set<Integer> set = new HashSet<>(List.of(1, 2, 2, 3, 4, 4));
        System.out.println(counts(list, set));

        HashMap<String, String> map = new HashMap<>();
        map.put("Marty", "Step");
        map.put("Stuart", "Reges");
        map.put("Jessica", "Miller");
        map.put("Amanda", "Camp");
        map.put("Meghan", "Miller");
        map.put("Hal", "Perkins");
        map.put("Brenda", "Miller");
        System.out.println(hasDuplicateValue(map));

        List<String> list2 = new ArrayList<>(List.of("duck", "duck", "duck", "goose"));
        List<String> list3 = new ArrayList<>(List.of("duck", "duck", "goose"));
        System.out.println(hasThree(list2)); //true
        System.out.println(hasThree(list3)); //false
    }

    //https://www.codestepbystep.com/r/problem/view/java/collections/map/CountNames
    //Doesn't meet the problems expectations
    public void countNames() {

        Scanner scanner = new Scanner(System.in);
        LinkedHashMap<String, Integer> names = new LinkedHashMap<>();
        boolean on = true;

        while (on) {
            IO.println("Enter a name.\nEnter nothing when finished.");
            String s = scanner.nextLine();

            if (s.isEmpty()) on = false;

            if (on) {
                if (!names.containsKey(s)) {
                    names.put(s, 1);
                } else {
                    int value = names.get(s) + 1;
                    names.put(s, value);
                }
            }
        }

        for (Map.Entry entry : names.entrySet()) {
            System.out.println("Entry [" + entry.getKey() + "] has count " + entry.getValue());
        }
    }

    //https://www.codestepbystep.com/r/problem/view/java/collections/map/counts
    public HashMap<Integer, Integer> counts(List<Integer> list, Set<Integer> set) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (Integer i : set) {
            map.put(i, 0);
        }

        for (Integer i : list) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            }
        }

        return map;
    }

    //https://www.codestepbystep.com/r/problem/view/java/collections/map/hasDuplicateValue
    public boolean hasDuplicateValue(Map<String, String> names) {
        Set<String> last_names = new HashSet<>();

        for (Map.Entry<String, String> entry : names.entrySet()) {
            if (last_names.contains(entry.getValue())) {
                return true;
            } else {
                last_names.add(entry.getValue());
            }
        }
        return false;
    }

    //https://www.codestepbystep.com/r/problem/view/java/collections/map/hasThree
    public boolean hasThree(List<String> tokens) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String token : tokens) {
            map.put(token, map.getOrDefault(token, 0) + 1);

            if (map.containsValue(3)) return true;
        }
        return false;
    }
}