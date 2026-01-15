import java.io.IO;

public class Writing {
    void main() {
        IO.println(sum(5));
        repeat(new String("ligma"), 5);
        IO.println(contains("Happy", 'y'));
        IO.println(contains("Happy", 'z'));
        IO.println(count("Happy", 'p'));
        IO.println(equals(new String("Grandma"), "Grandma"));
        IO.println(isSorted("abcdef"));
        IO.print(containsConsecutiveDuplicates(new String("apple")));
    }

    int sum(int n) {
        if (n <= 0) throw new IllegalArgumentException(":<(");
        int derp = n;
        if (derp == 1) {
            return derp;
        } else
            return derp + sum(derp - 1);
    }

    String repeat(String s, int n) {
        if (n <= 0) throw new IllegalArgumentException(":<(");
        int derp = n;
        IO.println(s + " ");
        if (derp == 1) {
            return s;
        } else {
            return repeat(s, derp - 1);
        }
    }

    boolean contains(String s, char c) {
        int ID = java.util.Objects.hash(c);

        for (int i = 0; i < s.length(); i++) {
            if (java.util.Objects.hash(s.charAt(i)) == ID) return true;
        }
        return false;
    }

    int count(String s, char c) {
        int n = 0;
        for (int i = 0; i < s.length(); i++)
            if (contains(s.charAt(i) + "", c)) n++;
        return n;
    }

    boolean equals(String s1, String s2) {
        //toying with substrings
        if (s1.length() != s2.length()) return false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.substring(i, i + 1).charAt(0) != s2.substring(i, i + 1).charAt(0)) return false;
        }
        return true;
    }

    boolean isSorted(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) return false;
        }
        return true;
    }

    boolean containsConsecutiveDuplicates(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) return true;
        }
        return false;
    }
}

boolean isSorted(String s) {
    if (s.length() == 1) return true;
    if (s.charAt(0) != s.charAt(1)) return true;
    return isSorted(s.substring(0, s.length() - 1));
}

void main() {
    IO.println(isSorted("apple"));
    IO.println(isSorted("rxa"));
}