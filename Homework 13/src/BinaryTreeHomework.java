import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public static <E> List<E> postorderList(BinaryTreeNode<E> root) {
        List<E> list = new ArrayList<>();
        postorderListHelper(root, list);
        return list;
    }

    public static <E> void postorderListHelper(BinaryTreeNode<E> root, List<E> list) {
        if (root == null) {
            return;
        }

        postorderListHelper(root.left, list);
        postorderListHelper(root.right, list);

        list.add(root.data);
    }

    public static <E> List<E> inorderList(BinaryTreeNode<E> root) {
        List<E> list = new ArrayList<>();
        inorderListHelper(root, list);
        return list;
    }

    public static <E> void inorderListHelper(BinaryTreeNode<E> root, List<E> list) {
        if (root == null) {
            return;
        }

        inorderListHelper(root.left, list);
        list.add(root.data);
        inorderListHelper(root.right, list);
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

    public static <E> boolean isProper(BinaryTreeNode<E> root) {
        if (root == null) {
            return true;
        }

        boolean left = root.left != null;
        boolean right = root.right != null;

        if (left ^ right) {
            return false;
        }

        return isProper(root.left) && isProper(root.right);
    }

    public static <E> boolean contains(BinaryTreeNode<E> root, E element) {
        if (root == null) {
            return false;
        }

        if (Objects.equals(root.data, element)) {
            return true;
        }

        return contains(root.left, element) || contains(root.right, element);
    }

    public static int sum(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        int value = (root.data == null) ? 0 : root.data;
        return value + sum(root.left) + sum(root.right);
    }

    public static int maxPathSum(BinaryTreeNode<Integer> root) {

        if (root == null) {
            return 0;
        }

        return root.data + Math.max(maxPathSum(root.left), maxPathSum(root.right));
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
                                new BinaryTreeNode<>(null), new BinaryTreeNode<>(12))))); // 10 nodes total, 9 leaves

        System.out.println(countLeaves(test_root));

        List<Integer> list1 = new ArrayList<>();
        List<List<Integer>> list2 = new ArrayList<>();

        //preorder
        list1 = preorderList(test_root);
        System.out.println("preorder: " + "\n" + list1 + "\n");

        //inorder
        list1 = inorderList(test_root);
        System.out.println("inorder: " + "\n" + list1 + "\n");

        //postorder
        list1 = postorderList(test_root);
        System.out.println("postorder: " + "\n" + list1 + "\n");

        //level-order
        list2 = levelOrderList(test_root);
        System.out.println("level-order: " + "\n" + list2 + "\n");

        //count leaves
        System.out.println("count leaves: " + countLeaves(test_root) + "\n"); //9

        BinaryTreeNode<Integer> improper_root = new BinaryTreeNode<>(5,
                new BinaryTreeNode<>(10,
                        new BinaryTreeNode<>(null),
                        null),
                new BinaryTreeNode<>(15,
                        new BinaryTreeNode<>(null),
                        new BinaryTreeNode<>(null)));
        //Note:
        //new BinaryTree<>(null); is still valid child
        //null is invalid as the branch totally doesn't exist

        System.out.println("count leaves: " + countLeaves(test_root) + "\n"); //9

        //isProper
        System.out.println(isProper(test_root)); //true
        System.out.println(isProper(improper_root)); //false

        IO.println();

        //contains
        System.out.println(contains(test_root, 15)); //true
        System.out.println(contains(test_root, 12)); //true
        System.out.println(contains(test_root, 0)); //false

        IO.println();

        System.out.println("sum of binary tree: " + sum(improper_root) + "\n"); //30
    }
}
