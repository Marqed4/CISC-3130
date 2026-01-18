import java.util.ArrayList;
import java.util.List;

public class BinaryTreeHomework {

    public class BinaryTreeNode<E> {
        public E data;
        public BinaryTreeNode<E> left, right;

        public BinaryTreeNode(E data) {
            this(data, null, null);
        }

        public BinaryTreeNode(E data, BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static <E> List<E> preorderList(BinaryTreeNode<E> root) {
        ArrayList<E> list = new ArrayList<>();
        preorderListHelper(root, list);
        return list;
    }

    public static <E> void preorderListHelper(BinaryTreeNode<E> root, ArrayList<E> list) {
        if (root == null) {
            list.add(root.data);
            preorderListHelper(root.left, list);
            preorderListHelper(root.right, list);
        }
    }

    //aka Breadth First
    public static <E> List<List<E>> levelOrderList(BinaryTreeNode<E> root) {

    }

    public static <E> void levelOrderListHelper(BinaryTreeNode<E> root, int level, List<List<E>> list) {

        if (root == null) {
            return;
        }

        if (list.size() <= level) {
            list.add(new ArrayList<>());
        }

        list.get(level).add(root.data);

        levelOrderListHelper(root.left, level + 1, list);
        levelOrderListHelper(root.right, level + 1, list);
    }

    ArrayList<ArrayList<Integer>> levelOrder(BinaryTreeNode<E> root) {}
}
