import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LeetCodeMethods {

    public static void main(String[] args) {
        System.out.println(uniqueOccurrences(new int[]{1, 2, 2, 2, 3, 3, 3})); //false
        System.out.println(uniqueOccurrences(new int[]{1, 2, 2, 4, 4, 4, 4, 3, 3, 3})); //true

        System.out.println(sumOfUnique(new int[]{1, 2, 3, 2})); //5

        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3)); //true
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2)); //false
    }

    //https://leetcode.com/problems/unique-number-of-occurrences/
    public static boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();

        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

//        System.out.println(map);

        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (set.contains(entry.getValue())) {
                return false;
            } else {
                set.add(entry.getValue());
            }
        }
        return true;
    }

    //https://leetcode.com/problems/sum-of-unique-elements/
    public static int sumOfUnique(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();

        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

//        System.out.println(map);

        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() < 2) {
                sum += entry.getKey();
            }
        }
        return sum;
    }

    /*<------ Next Following METHOD Won't Appear On Exams ------>*/

    //https://leetcode.com/problems/contains-duplicate-ii/
    public static boolean containsNearbyDuplicate(int[] nums, int k) {

        //O(n^2) Solution
        //Very Slow, very ugly LMAO
//        for (int i : nums) {
//                int hops = 0;
//                for (int j = i + 1; j < nums.length; j++) {
//                    if (hops == k) {
//                        break;
//                    } else {
//                        if (nums[j] == nums[i]) {
//                            return true;
//                        }
//                    }
//                    hops++;
//                }
//            }
//        return false;

        //O(n), HashMap Solution
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            } else {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                } else {
                    map.put(nums[i], i);
                }
            }
        }
        return false;
    }
}
