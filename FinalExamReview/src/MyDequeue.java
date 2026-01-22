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
        if (size == data.length) throw new IllegalStateException();
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
        return size();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}