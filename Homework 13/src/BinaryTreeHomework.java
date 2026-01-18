import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public class BinaryTreeHomework {

    public static class BinaryTreeNode<E> {
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
            return;
        }
            list.add(root.data);
            preorderListHelper(root.left, list);
            preorderListHelper(root.right, list);
    }

    //aka Breadth First
    public static <E> List<List<E>> levelOrderList(BinaryTreeNode<E> root) {
        List<List<E>> lists = new ArrayList<>();
        levelOrderListHelper(root, 0, lists);
        return lists;
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

    public static <E> int countLeaves(BinaryTreeNode<E> root) {
        return countLeavesHelper(root, 0);
    }

    public static <E> int countLeavesHelper(BinaryTreeNode<E> root, int nodeCount) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return countLeavesHelper(root.left, nodeCount) + countLeavesHelper(root.right, nodeCount);
    }

    void main() {
        BinaryTreeNode<Integer> test_root = new BinaryTreeNode<>(15,
                new BinaryTreeNode<>(3,
                        new BinaryTreeNode<>(3,
                                new BinaryTreeNode<>(null), new BinaryTreeNode<>(null)),
                        new BinaryTreeNode<>(10,
                                new BinaryTreeNode<>(null), new BinaryTreeNode<>(16))),
                new BinaryTreeNode<>(4,
                        new BinaryTreeNode<>(8,
                                new BinaryTreeNode<>(null), new BinaryTreeNode<>(null)),
                        new BinaryTreeNode<>(2,
                                new BinaryTreeNode<>(null), new BinaryTreeNode<>(9,
                                new BinaryTreeNode<>(null), new BinaryTreeNode<>(12))))); // 10 nodes total

        System.out.println(countLeaves(test_root));

        List<Integer> list = new ArrayList<>();
        list = preorderList(test_root);

        System.out.println(list);

    }
}
