import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

// TODO for homework
// Required representation: chain of doubly-linked nodes.
// The fields should be: a head pointer, a tail pointer, and an int size.
// Required method running times:
// - isEmpty, size, addFirst, addLast, getFirst, getLast, removeFirst, removeLast: O(1)
// - add, remove, get, set: O(n), but performing at most n/2 "hops" (i.e. going from node
//   to node.next, or from node to node.previous)
// - indexOf, lastIndexOf, toString: O(n)
// - iterator: O(1).
// The methods next() and hasNext() of Iterator returned by iterator() should run in O(1) time.
// The Iterator does not need to have a remove() method.
public class LinkedList3130<E> implements Deque3130<E>, List3130<E> {

    private static class ListNode3130<E> {
        E data;
        ListNode3130<E> next;
        ListNode3130<E> prev;

        public ListNode3130(E data) {
            this.data = data;
        }

        public ListNode3130(E data, ListNode3130<E> next, ListNode3130<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    ListNode3130<E> head;
    ListNode3130<E> tail;
    int size;

    LinkedList3130() {
        head = null;
        tail = null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    // deque methods

    @Override
    public void addFirst(E e) {
        ListNode3130<E> foo = new ListNode3130<>(e);

        if (head == null) {
            head = tail = foo;
        } else {
            foo.next = head;
            head.prev = foo;
            head = foo;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        ListNode3130<E> foo = new ListNode3130<>(e);

        if (tail == null) {
            head = tail = foo;
        } else {
            tail.next = foo;
            foo.prev = tail;
            tail = foo;
        }
        size++;
    }

    @Override
    public E getFirst() {
        return head.data;
    }

    @Override
    public E getLast() {
        return tail.data;
    }

    @Override
    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        E bar = head.data;

        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return bar;
    }

    @Override
    public E removeLast() {
        if (tail == null) throw new NoSuchElementException();

        E value = tail.data;

        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return value;
    }

    // list methods

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(element);
            return;
        }

        if (index == size()) {
            addLast(element);
            return;
        }

        ListNode3130<E> traverser = head;

        for (int i = 0; i < index - 1; i++) {
            traverser = traverser.next;
        }

        ListNode3130<E> foo = new ListNode3130<>(element);
        foo.next = traverser.next;
        foo.prev = traverser;

        traverser.next.prev = foo;
        traverser.next = foo;
        size++;
    }

    @Override
    public E remove(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size() - 1) {
            return removeLast();

        } else if (index == 0) {
            return removeFirst();
        }

        ListNode3130<E> traverser = head;

        for (int i = 0; i < index - 1; i++) {
            traverser = traverser.next;
        }
        E bar = traverser.next.data;
        traverser.next = traverser.next.next;

        if (traverser.next != null) {
            traverser.next.prev = traverser;
        }

        size--;
        return bar;
    }

    @Override
    public E get(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size() - 1) {
            return getLast();

        } else if (index == 0) {
            return getFirst();
        }

        ListNode3130<E> traverser = head;

        for (int i = 0; i < index; i++) {
            traverser = traverser.next;
        }

        return traverser.data;
    }

    @Override
    public E set(int index, E element) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size() - 1) {
            E foo = getLast();
            removeLast();
            addLast(element);
            return foo;
        }

        if (index == 0) {
            E foo = getFirst();
            removeFirst();
            addFirst(element);
            return foo;
        }

        ListNode3130<E> traverser = head;

        for (int i = 0; i < index; i++) {
            traverser = traverser.next;
        }

        E old_data = traverser.data;
        traverser.data = element;
        return old_data;
    }

    @Override
    public int indexOf(Object o) {
        ListNode3130<E> traverser = head;
        int position = 0;

        while (traverser != null) {
            if (Objects.equals(traverser.data, o)) {
                return position;
            }
            traverser = traverser.next;
            position++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        ListNode3130<E> traverser = tail;

        for (int i = size() - 1; i >= 0; i--) {
            if (Objects.equals(traverser.data, o)) {
                return i;
            }
            traverser = traverser.prev;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private ListNode3130<E> traverser = head;

            @Override
            public boolean hasNext() {
                return traverser != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }

                E foo = traverser.data;
                traverser = traverser.next;
                return foo;
            }
        };
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        ListNode3130<E> traverser = head;

        while (traverser.next != null) {
            sb.append(traverser.data + ", ");
            traverser = traverser.next;
        }

        return sb.append(traverser.data).append("]").toString();
    }
}

class myDemo {
    public static void main(String[] args) {
        long start = System.nanoTime();

        LinkedList3130<Integer> list = new LinkedList3130<>();
        list.addFirst(6);
        list.addFirst(7);
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        list.addLast(1);
        list.add(3, 15);

        for (Integer foo : list) {
            System.out.print(foo + " ");
        }

        System.out.println();
        System.out.println(list);
        list.add(7, 9);
        System.out.println();
        System.out.println(list.size());

        long finish = System.nanoTime();
        System.out.println(( "Program has taken: " + (finish - start) / 1_000_000) + " milliseconds");
    }
}