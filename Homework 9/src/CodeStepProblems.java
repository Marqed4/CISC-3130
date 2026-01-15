import java.util.ArrayList;
import java.util.List;

public class CodeStepProblems {

    public static void arrayListMystery1(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int n = list.get(i);
            if (n % 10 == 0) {
                list.remove(i);
                list.add(n);
            }
        }
        System.out.println(list);
    }

    public static void collectionMystery1(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int n = list.get(i);
            list.remove(i);
            if (n % 2 == 0) {
                list.add(i);
            }
        }
        System.out.println(list);
    }

    void main() {
        arrayListMystery1(new ArrayList<>(List.of(1, 20, 3, 40)));
        arrayListMystery1(new ArrayList<>(List.of(80, 3, 40, 20, 7)));
        collectionMystery1(new ArrayList<>(List.of(5, 2, 5, 2)));
    }
}

