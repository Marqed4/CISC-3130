import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public interface Stack3130<E> {
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

class GenericStack<E> implements Stack3130<E> {
    int size = 0;
    ArrayList<E> list;

    GenericStack() {
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
        GenericStack<E> stack = new GenericStack<>();

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
class GenricMethods {
    public <E> boolean contains(Stack3130<E> stack3130, Object o) {
        GenericStack<E> stack = new GenericStack<>();
        boolean found = false;
        while (!stack3130.isEmpty()) {
            if (Objects.equals(stack3130.peek(), o)) found = true;
            stack.push(stack3130.pop());
        }

        while (!stack.isEmpty()) {
            stack3130.push(stack.pop());
        }
        return found;
    }

    public <E> boolean equals(Stack3130<E> o1, Stack3130<E> o2) {
        GenericStack<E> stack = new GenericStack<>();
        boolean equal = true;
        int size = 0;

        try {
            while (!o1.isEmpty() && !o2.isEmpty()) {
                stack.push(o1.pop());
                stack.push(o2.pop());
                size++;
            }
        } catch (Exception e) {
            while (!stack.isEmpty()) {
                o1.push(stack.pop());
                o2.push(stack.pop());

                return false;
            }
        }

        while (!stack.isEmpty()) {
            E temp = stack.pop();
            if (!Objects.equals(temp, stack.peek())) equal = false;

            o1.push(temp);
            o2.push(stack.pop());
        }

        return equal;
    }

    void main() {
        GenericStack<Integer> stack = new GenericStack<>();
        stack.push(1);
        stack.push(12);
        stack.push(65);

        IO.println(stack);
        IO.println(stack);

        GenericStack<Integer> otherStack = new GenericStack<>();
        otherStack.push(1);
        otherStack.push(12);
        otherStack.push(65);
//    otherStack.push(75); // should catch exception and return false

        IO.println(stack);
        IO.println(otherStack);

        IO.println(equals(stack, otherStack));
    }

    class ProfessorAnswer {
        public <E> boolean equals(Stack3130<E> stack1, Stack3130<E> stack2) {
            Stack3130<E> aux = new GenericStack<E>(); // or LinkedStack3130
            boolean foundMismatch = false;

            while (!stack1.isEmpty() && !stack2.isEmpty() && !foundMismatch) {
                E element1 = stack1.pop();
                E element2 = stack2.pop();

                if (!Objects.equals(element1, element2)) {
                    foundMismatch = true;
                }

                aux.push(element1);
                aux.push(element2);
            }

            boolean haveSameSize = stack1.isEmpty() && stack2.isEmpty();

            while (!aux.isEmpty()) {
                stack2.push(aux.pop());
                stack1.push(aux.pop());
            }

            return haveSameSize && !foundMismatch;
        }
    }
}
