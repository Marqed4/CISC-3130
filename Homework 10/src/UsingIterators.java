import java.util.*;

public class UsingIterators {
    //Q1
    public List<String> removeEvenLength(List<String> list) {

        Iterator<String> it = list.iterator();
        for (Iterator<String> iter = it; iter.hasNext(); ) {
            String words = iter.next();
            if (words.length() % 2 == 0) {
                it.remove();
            }
        }
        return list;
    }

    //2
    public SequencedCollection<Integer> removeAndReturnEvens(SequencedCollection<Integer> list) {
        SequencedCollection<Integer> list2 = new LinkedList<>();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer fizz = it.next();

            if (fizz % 2 == 0) {
                list2.add(fizz);
                it.remove();
            }
        }
        return list2;
    }

    //3
    public <E> void removeEveryOccurence(Collection<E> collection, Object o) {
        Iterator<E> it = collection.iterator();

        while (it.hasNext()) {
            Object buzz = it.next();
            if (Objects.equals(buzz, o)) {
                it.remove();
            }
        }
    }

    //4
    public <E> void removeDuplicatesFromOccurance(SequencedCollection<E> collection) {
        Iterator<E> it = collection.iterator();
        HashSet<E> set = new HashSet<>();

        while (it.hasNext()) {
            E e = it.next();

            if (set.contains(e)) {
                it.remove();
            } else {
                set.add(e);
            }
        }
    }

    //5
    public <E extends Comparable<E>> E max(Collection<E> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }

        Iterator<E> it = collection.iterator();
        E max = it.next();

        while (it.hasNext()) {
            E current = it.next();
            if (current.compareTo(max) > 0) {
                max = current;
            }
        }

        return max;
    }

    void main() {
        //Testing
        List<String> list1 = new ArrayList<>(List.of("rack", "trim", "ziggy"));
        IO.println(removeEvenLength(list1));

        ArrayList<Integer> list2 = new ArrayList<>(List.of(5, 4, 3));
        list2.add(2, 2);
        IO.println(removeAndReturnEvens(list2));
        IO.println((list2));

        LinkedList<Number> bucket1 = new LinkedList<>();
        bucket1.add(6967);
        bucket1.add(1);
        bucket1.add(new Integer(Math.abs(-1)));
        bucket1.add(6);
        bucket1.add(6967);
        LinkedList<Integer> bucket2 = new LinkedList<>();
        ArrayList<LinkedList<Number>> table = new ArrayList<>();
        table.add(bucket1);
        removeEveryOccurence(bucket1, new Integer(1));
        IO.println((table));

        list1 = new ArrayList<>(List.of("RX 480", "GTX 1070", "SUPERLIGHT G-PRO WIRELESS",
                "GTX 1050 TI", "RTX 2060", "RTX 3080", "RTX 5080 SUPER", "RX 480", "GTX 1070",
                "RTX 3080", "RTX 2060", "ZOWIE EC2A", "FINALMOUSE CAPETOWN", "RTX 3050",
                "ZOWIE EC2B", "SUPERLIGHT G-PRO WIRELESS"));


        removeDuplicatesFromOccurance(list1);
        IO.println((list1));

        list2 = new ArrayList<>(List.of(5, 4, 3, 2, 1));
        IO.println(max(list2));
    }
}
