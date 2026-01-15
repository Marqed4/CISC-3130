import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;

interface Queue3130<E> {
    int size();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E peek();

    @Override
    String toString();
}

class LinkedQueue3130<E> implements Queue3130<E> {

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this(data, null);
        }

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> head = null, tail = null;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    @Override
    public void enqueue(E e) {
        if (isEmpty()) {
            head = tail = new Node<>(e);
        } else {
            tail = tail.next = new Node<>(e);
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        E foo = head.data;
        head = head.next;
        size--;

        if (head == null) tail = null;

        return foo;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException();

        return head.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;

        while (current != null) {
            sb.append(current.data);

            if (current.next != null) {
                sb.append(", ");
            }

            current = current.next;
        }

        return sb.append("]").toString();
    }
}

interface Stack3130<E> {
    /**
     * Determines whether the stack is empty.
     */
    boolean isEmpty();

    /**
     * Pushes the specified element onto the top of the stack.
     */
    void push(E e);

    /**
     * Removes top element of the stack and returns it.
     * Throws a NoSuchElementException if the stack is empty.
     */
    E pop();

    /**
     * Returns top element of the stack without removing it.
     * Throws a NoSuchElementException if the stack is empty.
     */
    E peek();

    /**
     * Returns a String containing the elements listed from top to bottom.
     */
    @Override
    String toString();
}

class ArrayStack3130<E> implements Stack3130<E> {
    int size = 0;
    ArrayList<E> list;

    ArrayStack3130() {
        list = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(E e) {
        list.add(e);
        size++;
    }

    @Override
    public E pop() {
        E ref = list.get(size - 1);
        list.remove(size - 1);
        size--;
        return ref;
    }

    @Override
    public E peek() {
        return list.get(size - 1);
    }

    public String toString() {
        ArrayStack3130<E> stack = new ArrayStack3130<>();

        StringBuilder sb = new StringBuilder("[");
        while (!isEmpty()) {
            stack.push(pop());
        }

        while (!stack.isEmpty()) {
            sb.append(stack.peek() + ", ");
            push(stack.pop());
        }

        sb.replace(sb.length() - 2, sb.length(), "]");
        return sb.toString();
    }
}


public class GenericMethods {

    //contains without any auxiliary collections
    public <E> boolean contains(LinkedQueue3130<E> foo, Object o) {
        if (foo.isEmpty()) return false;
        boolean found_state = false;
        for (int i = 0; i < foo.size(); i++) {
            if (Objects.equals(foo.peek(), o)) found_state = true;

            var x = foo.dequeue();
            foo.enqueue(x);
        }
        return found_state;
    }

    public <E> boolean equals(LinkedQueue3130<E> foo, LinkedQueue3130<E> bar) {
        if (foo.size() != bar.size()) {
            return false;
        }

        boolean isEqual = true;

        for (int i = 0; i < foo.size(); i++) {
            if (!Objects.equals(foo.peek(), bar.peek())) isEqual = false;

            var x = foo.dequeue();
            foo.enqueue(x);

            x = bar.dequeue();
            bar.enqueue(x);
        }

        return isEqual;
    }

    public <E> LinkedQueue3130<E> mirror(LinkedQueue3130<E> foo) {

        ArrayStack3130<E> bar = new ArrayStack3130<E>();
        int init_size = foo.size();

        for (int i = 0; i < init_size; i++) {
            var x = foo.dequeue();
            bar.push(x);
            //adds it right back
            foo.enqueue(x);
        }

        //add in reverse order
        for (int i = 0; i < init_size; i++) {
            foo.enqueue(bar.pop());
        }

        return foo;
    }

    public <E> LinkedQueue3130<E> twice(LinkedQueue3130<E> foo) {
        int init_size = foo.size();

        for (int i = 0; i < init_size; i++) {
            var x = foo.dequeue();
            foo.enqueue(x);
            foo.enqueue(x);
        }

        return foo;
    }

    void main() {
        LinkedQueue3130<Object> queue = new LinkedQueue3130<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");

        IO.println(queue.toString());
        LinkedQueue3130<Object> queue2 = queue;
        IO.println(twice(queue2));
        IO.println(equals(queue, queue2));
        IO.println(mirror(queue2));
    }
}