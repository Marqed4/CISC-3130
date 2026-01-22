import java.util.*;

@SuppressWarnings("unchecked")
public class MyDequeue<E> implements Deque3130<E>, Iterable<E> {

    private E[] data;
    private int indexOfFirst = 0;
    private int indexOfLast = 0;
    private int size = 0;

    public MyDequeue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public void addFirst(E e) {
        if (size == data.length) throw new IllegalStateException();
        indexOfFirst = (indexOfFirst - 1 + data.length) % data.length;
        data[indexOfFirst] = e;
        size++;
    }

    @Override
    public void addLast(E e) {

        if (size == data.length) {
            int originalSize = size;
            E[] temp = (E[]) new Object[(data.length * 2) + 1];

            for (int i = 0; i < originalSize; i++) {
                temp[i] = removeFirst();
            }

            data = temp;
            indexOfFirst = 0;
            indexOfLast = originalSize;
            size = originalSize;
        }

        data[indexOfLast] = e;
        indexOfLast = (indexOfLast + 1) % data.length;
        size++;
    }


    @Override
    public E removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        E value = data[indexOfFirst];
        data[indexOfFirst] = null;
        indexOfFirst = (indexOfFirst + 1) % data.length;
        size--;
        return value;
    }

    @Override
    public E removeLast() {
        if (size == 0) throw new NoSuchElementException();
        indexOfLast = (indexOfLast - 1 + data.length) % data.length;
        E value = data[indexOfLast];
        data[indexOfLast] = null;
        size--;
        return value;
    }

    @Override
    public E getFirst() {
        return data[indexOfFirst];
    }

    @Override
    public E getLast() {
        int lastIndex = (indexOfLast - 1 + data.length) % data.length;
        return data[lastIndex];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int count = 0;
            int idx = indexOfFirst;

            public boolean hasNext() {
                return count < size;
            }

            public E next() {
                E val = data[idx];
                idx = (idx + 1) % data.length;
                count++;
                return val;
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int size = this.size;
        for (int i = 0; i < size; i++) {
            if (size - 1 > i) {
                E datum = this.removeFirst();
                this.addLast(datum);
                sb.append(datum).append(", ");
            } else {
                E datum = this.removeFirst();
                this.addLast(datum);
                sb.append(datum);
            }
        }

        return sb.append("]").toString();
    }

    public static void main() {

        MyDequeue<Integer> test = new MyDequeue<>(3);
        test.enqueue(10);
        test.enqueue(15);
        test.enqueue(20);

        System.out.println(test); //10, 15, 20

        test.dequeue();
        test.dequeue();

        System.out.println(test); //null, null, 20

        test.enqueue(25);
        test.enqueue(30);

        System.out.println(test); //25, 30, 20, elements are added starting from the empty spots in the Dequeue

        test.enqueue(35); //the addition triggers the resize

        System.out.println(test); //20, 25, 30, 35
    }
}