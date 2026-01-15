import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CodeStepProblems {
    public static void collectionMystery6(Stack<Integer> s) {
        Queue<Integer> q = new LinkedList<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        while (!s.isEmpty()) {
            if (s.peek() % 2 == 0) {
                q.add(s.pop());
            } else {
                s2.push(s.pop());
            }
        }

        while (!q.isEmpty()) {
            s.push(q.remove());
        }
        while (!s2.isEmpty()) {
            s.push(s2.pop());
        }

        System.out.println(s);
    }

    public static void collectionMystery10(Stack<Integer> stack, int n) {
        Stack<Integer> stack2 = new Stack<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();

        while (stack.size() > n) {
            queue.add(stack.pop());
        }
        while (!stack.isEmpty()) {
            int element = stack.pop();
            stack2.push(element);
            if (element % 2 == 0) {
                queue.add(element);
            }
        }
        while (!queue.isEmpty()) {
            stack.push(queue.remove());
        }
        while (!stack2.isEmpty()) {
            stack.push(stack2.pop());
        }

        System.out.println(stack);
    }

    void main() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        collectionMystery6(stack);

        stack.clear();

        stack.push(42);
        stack.push(3);
        stack.push(12);
        stack.push(15);
        stack.push(9);
        stack.push(71);
        stack.push(88);

        collectionMystery6(stack);

        stack.clear();

        stack.push(67);
        stack.push(29);
        stack.push(115);
        stack.push(84);
        stack.push(33);
        stack.push(71);
        stack.push(90);

        collectionMystery10(stack, 5);
    }
}
