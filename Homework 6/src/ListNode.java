import java.util.Objects;

public class ListNode<E> {
    E data;
    ListNode<E> next;

    public ListNode(E data) {
        this(data, null);
    }

    public ListNode(E data, ListNode<E> next) {
        this.data = data;
        this.next = next;
    }
}

class Question1 {
    public static void printList(ListNode head) {
        while (head != null) {
            IO.print(head.data + " ");
            head = head.next;
        }
    }

    void main() {
        ListNode<Integer> list = new ListNode<>(5, new ListNode<>(4, new ListNode<>(3, new ListNode<>(2))));
        ListNode<Integer> curr = list;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new ListNode<>(42);
        printList(list);
    }
}

/*Recursion With List Nodes*/
class Question2 {
    public static <E> String concat(ListNode<E> head) {
        if (head == null) return "";
        return head.data + concat(head.next);
    }

    public static <E> boolean contains(ListNode<E> head, Object o) {
        if (head == null) return false;
        if (Objects.equals(head.data, o)) return true;
        return contains(head.next, o);
    }

    public static <E> int count(ListNode<E> head, Object o) {
        if (head == null) return 0;
        if (Objects.equals(head.data, o)) {
            return 1 + count(head.next, o);
        }
        return count(head.next, o);
    }

    public static <E> boolean containsDuplicate(ListNode<E> head) {
        if (head == null) return false;
        if (contains(head.next, head.data)) return true;
        return containsDuplicate(head.next);
    }

    public static <E> boolean containsConsecutiveDuplicate(ListNode<E> head) {
        if (head == null || head.next == null) return false;
        if (Objects.equals(head.data, head.next.data)) return true;
        return containsConsecutiveDuplicate(head.next);
    }

    public static <E extends Comparable<E>> boolean isSorted(ListNode<E> head) {
        if (head == null || head.next == null) return true;
        if (head.data.compareTo(head.next.data) >= 1) return false;
        return isSorted(head.next);
    }

    public static <E> boolean equals(ListNode<E> head1, ListNode<E> head2) {
        if (head1 == null && head2 == null) return true;
        else if (head1 == null || head2 == null) return false;
        return Objects.equals(head1.data, head2.data) && equals(head1.next, head2.next);
    }
}