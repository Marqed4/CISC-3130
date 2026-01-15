import java.util.Objects;

private class ArrayStringBoundedList implements StringBoundedList {
    private String[] internalArray;
    private int size;

    public ArrayStringBoundedList(int size) {
        if (size <= 0) throw new IllegalArgumentException("Illegal size: " + size);
        this.size = 0;
        internalArray = new String[size];
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
    public void add(String s) {
        if (size() == capacity()) throw new ArrayIndexOutOfBoundsException();
        internalArray[size()] = s;
        size++;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        return internalArray[index];
    }

    @Override
    public String set(int index, String element) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + internalArray.length);
        String temp = internalArray[index];
        internalArray[index] = element;
        return temp;
    }

    @Override
    public int indexOf(String s) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(internalArray[i], s)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String s) {
        for (int i = size() - 1; i >= 0; i--) {
            if (Objects.equals(internalArray[i], (s))) return i;
        }
        return -1;
    }

    @Override
    public void clear() {
        internalArray = new String[internalArray.length];
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

void main() {
    ArrayStringBoundedList list = new ArrayStringBoundedList(5);
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