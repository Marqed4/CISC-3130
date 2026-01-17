import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMultiset<E> implements Multiset<E> {

    private HashMap<E, Integer> internal_map;
    private int size;

    HashMultiset() {
        internal_map = new HashMap<>();
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E element) {
        internal_map.put(element, internal_map.getOrDefault(element, 0) + 1);
        size++;
    }

    @Override
    public void add(E element, int occurrences) throws IllegalArgumentException {

        if (occurrences < 0) {
            throw new IllegalArgumentException();
        }

        int buzz = internal_map.getOrDefault(element, 0);
        internal_map.put(element, buzz + occurrences);
        size += occurrences;
    }

    @Override
    public boolean contains(E element) {
        return internal_map.containsKey(element);
    }

    @Override
    public int count(E element) {
        return internal_map.getOrDefault(element, 0);
    }

    @Override
    public boolean remove(E element) {
        if (internal_map.containsKey(element)) {
            internal_map.put(element, internal_map.getOrDefault(element, 0) - 1);
            size--;

            if (internal_map.get(element) == 0) internal_map.remove(element);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int removeAllOccurrences(E element) {

        if (internal_map.containsKey(element)) {

            int foo = internal_map.get(element);

            internal_map.remove(element);

            size -= foo;
            return foo;

        } else {

            return 0;
        }
    }

    @Override
    public int remove(E element, int occurrences) throws IllegalArgumentException {
        if (occurrences <= 0) throw new IllegalArgumentException();
        if (!internal_map.containsKey(element)) return 0;

        int foo = internal_map.get(element);

        if (occurrences >= foo) {
            removeAllOccurrences(element);
            return foo;
        }

        internal_map.put(element, internal_map.getOrDefault(element, 0) - occurrences);
        size -= occurrences;

        if (internal_map.get(element) == 0) internal_map.remove(element);
        return foo;
    }

    @Override
    public Iterator<E> iterator() {
        ArrayList<E> elements = new ArrayList<>();

        for (HashMap.Entry<E, Integer> entries : internal_map.entrySet()) {
            E element = entries.getKey();
            int occurrence = entries.getValue();

            for (int i = 0; i < occurrence; i++) {
                elements.add(element);
            }
        }
        return elements.iterator();
    }

    @Override
    public Set<E> elementSet() {
        return internal_map.keySet();
    }
}

class deme {
    void main() {
        HashMultiset<String> County = new HashMultiset<>();
        ;
        County.add("New York County");
        County.add("Kings County");
        County.add("Queens County");
        County.add("Richmond County");
        County.add("Bronx County");
        County.add("New York County");
        IO.println(County.size()); // 6

        County.remove("New York County", 1);
        IO.println(County.size()); // 5

        County.remove("Queens County", 1);
        County.remove("Richmond County", 1);

        County.add("Kings County");
        County.add("Kings County");
        County.add("Kings County");
        County.add("Kings County");
        County.add("Kings County");
        County.add("Kings County");
        County.add("Kings County");
        County.add("Kings County");
        County.add("Kings County");
        County.add("Kings County");

        County.removeAllOccurrences("Kings County");

        IO.println(County.size());
        IO.println(County.size());
        IO.println(County.count("Kings County"));
        IO.println(County.elementSet());
    }
}