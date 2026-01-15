import java.util.Objects;

public interface BoundedList<E> {

    int capacity();

    int size();

    void add(E elm);

    E get(int index);

    E set(int index, E elm);

    int indexOf(E elm);

    int lastIndexOf(E elm);

    void clear();

    default boolean isEmpty() {
        return (size() == 0);
    }

    default boolean isFull() {
        return (size() == capacity());
    }

    default E getFirst() {
            return get(0);
    }

    default E getLast() {
        return get(size() - 1);
    }

    default E setFirst(E elm) {
        return this.set(0, elm);
    }

    default boolean contains(E elm) {
        for (int i = 0; i < this.size(); i++) {
            if (java.util.Objects.equals(this.get(i), elm)) return true;
        }
        return false;
    }

}

class ArrayBoundedList<E> implements BoundedList<E> {

    private E[] internalArray;
    private int size;

    public ArrayBoundedList(int size) {
        if (size <= 0) throw new IllegalArgumentException("Illegal size: " + size);
        this.size = 0;
        internalArray = (E[]) new Object[size];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return internalArray.length;
    }

    @Override
    public void add(E elm) {
        if (size() == capacity()) throw new ArrayIndexOutOfBoundsException();
        internalArray[size()] = elm;
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        return internalArray[index];
    }

    @Override
    public E set(int index, E elm) {
            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + internalArray.length);

            E temp = internalArray[index];
            internalArray[index] = elm;
            return temp;
        }

    @Override
    public int indexOf(E elm) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(internalArray[i], elm)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E elm) {
        for (int i = size() - 1; i >= 0; i--) {
            if (Objects.equals(internalArray[i], (elm))) return i;
        }
        return -1;
    }

    @Override
    public void clear() {
        internalArray = (E[]) new Object[capacity()];
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size - 1; i++) {
            sb.append(internalArray[i]).append(", ");
        }

        sb.append(internalArray[size - 1]).append("]");
        return sb.toString();
    }
}

class Demo {
    void main() {
        ArrayBoundedList list = new ArrayBoundedList(5);
        list.add("first");
        list.add("second");
        list.add("third");
        list.set(2, "fourth");
        list.set(1, "second");
        list.add("fifth");
        list.add("fifth");

        System.out.println(list.indexOf("fourth"));

        System.out.println(list);
        System.out.println(list.lastIndexOf("second"));
        System.out.println(list.lastIndexOf("first"));
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.isEmpty());
        System.out.println(list.isFull());
        System.out.println(list.size());
        System.out.println(list.capacity());
        list.clear();
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list.capacity());
        System.out.println(list);
        list.add("seventh");
        System.out.println(list.size());
        list.add("eighth");
        System.out.println(list.size());
        list.add("ninth");
        System.out.println(list.size());
        list.add("first");
        System.out.println(list.size());
        list.add("second");
        System.out.println(list);
//    list.add("third");
        System.out.println(list.set(0, "twenty-four"));
    }
}