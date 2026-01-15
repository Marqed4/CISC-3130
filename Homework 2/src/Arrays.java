import java.io.IO;

public class Arrays {

    void main() {
        IO.println(sum(new int[]{5, 4, 3, 2, 1}));
        IO.println(isPalindrome(new int[]{1, 0, 1}));
        IO.println(isPalindrome(new int[]{1, 0, 0}));
        IO.println(isPalindrome(new int[]{1, 0, 0, 1}));
        IO.println(isPalindrome(new int[]{1, 0, 5, 2, 2, 5, 0, 1}));
    }

    public int sum(int[] a) {
        if (a.length == 0) {
            return 0;
        } else {
            int[] x = java.util.Arrays.copyOf(a, a.length - 1);
            return a[a.length - 1] + sum(x);
        }
    }

    public boolean isPalindrome(int[] a) {
        if (a.length <= 2)
            return true;
        else {
            if (a[0] != a[a.length - 1]) return false;
            return isPalindrome(java.util.Arrays.copyOfRange(a, a.length - (a.length - 1), a.length - 1));
        }
    }
}
