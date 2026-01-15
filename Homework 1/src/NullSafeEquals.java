public class NullSafeEquals {}
class ObjectUtils {
    public static boolean nullSafeEquals(Object o1, Object o2) {
        // TODO
        return (o1 == o2) || (o1 != null && o1.equals(o2));

    }

    // tests; the comments indicate the desired results
    static void main() {
        IO.println(ObjectUtils.nullSafeEquals(null, null)); // true

        IO.println(ObjectUtils.nullSafeEquals(null, "hello")); // false
        IO.println(ObjectUtils.nullSafeEquals("hello", null)); // false

        IO.println(ObjectUtils.nullSafeEquals("hello", "hello"));             // true
        IO.println(ObjectUtils.nullSafeEquals("hello", new String("hello"))); // true
    }
}

class ArrayUtils1 extends ObjectUtils {
    public static int indexOf(String[] a, String s) {
        for (int index = 0; index < a.length; index++)
            if (nullSafeEquals(a[index], s)) return index;
        return -1;
    }

    public static boolean allEqualTo(String[] a, String s) {
        for (String i : a) {
            if (!nullSafeEquals(i, s)) return false;
        }
        return true;
    }

    public static boolean someEqualTo(String[] a, String s) {
       return (indexOf(a, s) >= 0);
    }

    public static boolean noneEqualTo(String[] a, String s) {
        return (indexOf(a, s) == -1);
    }

    public static boolean equals(String[] a1, String[] a2) {
        if (a1.length != a2.length) return false;
        int size = 0;
        for (String i : a1) {
            for (int j = 0; j < a1.length; j++)
                if (nullSafeEquals(i, a2[j])) {
                    size++; break;
                }
        }
        return (size == a1.length);
    }
}

// tests; the comments indicate the desired results
class ArrayUtils1Test {
    static void main() {
        testIndexOf();
        testAllEqualTo();
        testSomeEqualTo();
        testNoneEqualTo();
        testEquals();
    }

    private static void testIndexOf() {
        IO.println("Testing indexOf");
        IO.println(ArrayUtils1.indexOf(new String[]{"a", "b", "c"}, "a")); // 0
        IO.println(ArrayUtils1.indexOf(new String[]{"a", "b", "c"}, "b")); // 1
        IO.println(ArrayUtils1.indexOf(new String[]{"a", "b", "c"}, "c")); // 2
        IO.println(ArrayUtils1.indexOf(new String[]{"a", "b", "c"}, "d")); // -1
        IO.println(ArrayUtils1.indexOf(new String[]{"a", "b", "a", "c"}, "a")); // 0
        IO.println(ArrayUtils1.indexOf(new String[]{null, "b", "c"}, null)); // 0
        IO.println(ArrayUtils1.indexOf(new String[]{"a", null, "c"}, null)); // 1
        IO.println(ArrayUtils1.indexOf(new String[]{}, "a")); // -1
        IO.println(ArrayUtils1.indexOf(new String[]{new String("a")}, new String("a"))); // 0
        IO.println();
    }

    private static void testAllEqualTo() {
        IO.println("Testing allEqualTo");
        IO.println(ArrayUtils1.allEqualTo(new String[]{"a", "a", "a"}, "a")); // true
        IO.println(ArrayUtils1.allEqualTo(new String[]{"a", "b", "a"}, "a")); // false
        IO.println(ArrayUtils1.allEqualTo(new String[]{"b", "b", "b"}, "b")); // true
        IO.println(ArrayUtils1.allEqualTo(new String[]{"b", "b", "c"}, "b")); // false
        IO.println(ArrayUtils1.allEqualTo(new String[]{}, "a")); // true
        IO.println(ArrayUtils1.allEqualTo(new String[]{null, null, null}, null)); // true
        IO.println(ArrayUtils1.allEqualTo(new String[]{null, "a", null}, null)); // false
        IO.println(ArrayUtils1.allEqualTo(new String[]{"a", "a", "a"}, "b")); // false
        IO.println(ArrayUtils1.allEqualTo(new String[]{new String("a"), new String("a"), "a"}, new String("a"))); // true
        IO.println();
    }

    private static void testSomeEqualTo() {
        IO.println("Testing someEqualTo");
        IO.println(ArrayUtils1.someEqualTo(new String[]{"a", "b", "c"}, "a")); // true
        IO.println(ArrayUtils1.someEqualTo(new String[]{"a", "b", "c"}, "b")); // true
        IO.println(ArrayUtils1.someEqualTo(new String[]{"a", "b", "c"}, "d")); // false
        IO.println(ArrayUtils1.someEqualTo(new String[]{"a", "a", "a"}, "a")); // true
        IO.println(ArrayUtils1.someEqualTo(new String[]{"b", "b", "c"}, "b")); // true
        IO.println(ArrayUtils1.someEqualTo(new String[]{"b", "b", "c"}, "c")); // true
        IO.println(ArrayUtils1.someEqualTo(new String[]{"b", "b", "c"}, "d")); // false
        IO.println(ArrayUtils1.someEqualTo(new String[]{}, "a")); // false
        IO.println(ArrayUtils1.someEqualTo(new String[]{null, null, null}, null)); // true
        IO.println(ArrayUtils1.someEqualTo(new String[]{null, "a", null}, null)); // true
        IO.println(ArrayUtils1.someEqualTo(new String[]{"a", "a", "a"}, "b")); // false
        IO.println();
    }

    private static void testNoneEqualTo() {
        IO.println("Testing noneEqualTo");
        IO.println(ArrayUtils1.noneEqualTo(new String[]{"a", "b", "c"}, "d")); // true
        IO.println(ArrayUtils1.noneEqualTo(new String[]{"a", "b", "c"}, "a")); // false
        IO.println(ArrayUtils1.noneEqualTo(new String[]{"a", "b", "c"}, "b")); // false
        IO.println(ArrayUtils1.noneEqualTo(new String[]{"a", "a", "a"}, "b")); // true
        IO.println(ArrayUtils1.noneEqualTo(new String[]{"b", "b", "b"}, "a")); // true
        IO.println(ArrayUtils1.noneEqualTo(new String[]{"b", "b", "c"}, "d")); // true
        IO.println(ArrayUtils1.noneEqualTo(new String[]{"b", "b", "c"}, "c")); // false
        IO.println(ArrayUtils1.noneEqualTo(new String[]{}, "a")); // true
        IO.println(ArrayUtils1.noneEqualTo(new String[]{null, null, null}, null)); // false
        IO.println(ArrayUtils1.noneEqualTo(new String[]{null, "a", null}, null)); // false
        IO.println(ArrayUtils1.noneEqualTo(new String[]{"a", "a", "a"}, "b")); // true
        IO.println();
    }

    private static void testEquals() {
        IO.println("Testing equals");
        IO.println(ArrayUtils1.equals(new String[]{"a", "b", null}, new String[]{"a", "b"})); // false
        IO.println(ArrayUtils1.equals(new String[]{"a", "b", null}, new String[]{"a", "b", null})); // true
        IO.println(ArrayUtils1.equals(new String[]{"a", "b", "c"}, new String[]{"a", "b", "c"})); // true
        IO.println(ArrayUtils1.equals(new String[]{"a", "b", "c"}, new String[]{"a", "c", "b"})); // false
        IO.println(ArrayUtils1.equals(new String[]{"a", "b", "c"}, new String[]{"a", "b", "c", "d"})); // false
        IO.println(ArrayUtils1.equals(new String[]{"a", "b", "c"}, new String[]{"a", "b"})); // false
        IO.println(ArrayUtils1.equals(new String[]{null, "b", "c"}, new String[]{null, "b", "c"})); // true
        IO.println(ArrayUtils1.equals(new String[]{null, "b", "c"}, new String[]{"a", "b", "c"})); // false
        IO.println(ArrayUtils1.equals(new String[]{null, null, null}, new String[]{null, null, null})); // true
        IO.println(ArrayUtils1.equals(new String[]{null, "x", null}, new String[]{null, "x", "y"})); // false
        IO.println(ArrayUtils1.equals(new String[]{}, new String[]{})); // true
        IO.println(ArrayUtils1.equals(new String[]{}, new String[]{"a"})); // false
        IO.println(ArrayUtils1.equals(new String[]{"a"}, new String[]{})); // false
        IO.println(ArrayUtils1.equals(new String[]{new String("a")}, new String[]{new String("a")})); // true
        IO.println();
    }
}