import java.util.*;
import java.util.function.Function;

public class myDequeue<E> implements Deque3130<E>, Iterable<E>{

    List<E> list;
    int size;
    int indexOfFirst;
    int indexOfLast;

    //Starting with a size that resembles the question from Q4.

    myDequeue() {
        list = new ArrayList<>(3);
        indexOfFirst = 0;
        indexOfLast = 0;
    }

    myDequeue(int size) {
        list = new ArrayList<>(size);
        indexOfFirst = 0;
        indexOfLast = 0;
    }

    @Override
    public void addFirst(E e) {
        if (size == 0) {
            list.addFirst(e);
            indexOfFirst = 0;
            indexOfLast = 0;
        }

        list.addFirst(e);
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == list.size()) throw new IllegalStateException("Full");

        list.set(indexOfLast, e);
        indexOfLast = (indexOfLast + 1) % list.size();
        size++;
    }

    @Override
    public E getFirst() {
        return list.get(indexOfFirst);
    }

    @Override
    public E getLast() {
        return list.get(indexOfLast);
    }

    @Override
    public E removeFirst() {
        size--;
        E datum = list.get(indexOfFirst);
        list.set(indexOfFirst, null);
        indexOfFirst++;

        if (indexOfFirst == list.size() - 1) {

        }

        return datum;
    }

    @Override
    public E removeLast() {
        size--;
        E datum = list.remove(indexOfLast);
        list.set(indexOfLast, null);
        return datum;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return list.get(indexOfFirst);
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
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
