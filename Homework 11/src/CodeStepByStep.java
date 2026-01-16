

import java.util.*;

public class CodeStepByStep {

    //https://www.codestepbystep.com/r/problem/view/java/collections/set/maxLength
    public int maxLength(Set<String> strings) {

        int max = 0;
        for (String s : strings) {
            if (s.length() > max) {
                max = s.length();
            }
        }
        return max;
    }

    //https://www.codestepbystep.com/r/problem/view/java/collections/set/numUniqueValues
    public int numUniqueValues(List<Integer> ints) {
        HashSet<Integer> set = new HashSet<>(ints);
        return set.size();
    }

    //https://www.codestepbystep.com/r/problem/view/java/collections/set/numInCommon
    public int numInCommon(List<Integer> ints1, List<Integer> ints2) {

        int in_common = 0;

        HashSet<Integer> set1 = new HashSet<>(ints1);
        HashSet<Integer> set2 = new HashSet<>(ints2);

        for (Integer i : set1) {
            if (set2.contains(i)) {
                in_common++;
            }
        }

        return in_common;
    }
    //https://www.codestepbystep.com/r/problem/view/java/collections/set/unionSets
    public Set<Integer> unionSets(Set<Set<Integer>> table) {
        Set<Integer> auxSet = new HashSet<>();
        HashSet<Integer> auxHashSet = new HashSet<>();

        for (Set<Integer> setting : table) {

        }
    }

    void main() {
        Set<String> set = new HashSet<>(List.of("Stapler", "Paper", "Pen", "Pencil")); //7
        System.out.println(maxLength(set));

        List<Integer> list = new ArrayList<>(List.of(5, 5, 4, 4, 3, 2, 1, 1, 1, 7, 8, 6, 7));
        System.out.println(numUniqueValues(list));

        list = new ArrayList<>(List.of(5, 5, 4, 4, 50000, 5));
        List<Integer> otherList = new ArrayList<>(List.of(5, 5, 4, 4, 50000));
        System.out.println(numInCommon(list, otherList));

    }
}
