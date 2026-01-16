import java.util.ArrayList;
import java.util.Iterator;

public class Range implements Iterable<Integer> {
    private int start;
    private int end;
    private int increment;
    ArrayList<Integer> internal_array;

    Range(int start, int end, int increment) {
        if (start > end || increment < 1) {
            throw new IllegalArgumentException("start > end");
        }

        this.start = start;
        this.end = end;
        this.increment = increment;

        internal_array = new ArrayList<>(end - start + 1);
        for (int i = start; i < end; i += increment) {
            internal_array.add(i);
        }
    }

    Range(int start, int end) {
        this(start, end, 1);
    }

    Range() {
        this(0, 3, 1);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            int size = internal_array.size();
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }

                return internal_array.get(index++);
            }
        };
    }
}

class Test {
    void main() {
        long start = System.nanoTime();
        Range range = new Range(5, 25, 5);
        Range range2 = new Range();

        for (Integer x : range) {
            System.out.println(x);
        }

        for (int x : range2) {
            System.out.println(x);
        }

        long finish = System.nanoTime();
        System.out.println(( "Program has taken: " + (finish - start) / 1_000_000) + " milliseconds");
    }
}

//Why am I awake 10:54 doing this.
