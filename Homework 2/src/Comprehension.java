import java.io.IO;
public class Comprehension {}

class Mystery1 {
    public void main(String[] args) {
        IO.println((mystery(3)));
    }

    static int mystery(int n) {
        IO.println(n);
        if (n == 0) {
            return 1;
        } else {
            return 3 * mystery(n - 1);
        }
    }
}


class Mystery6 {
    static void main() {
        mystery(3);
    }

    static void mystery(int n) {
        if (n > 0) {
            mystery(n - 2);
        }

        IO.print(n + " ");

        if (n > 0) {
            mystery(n - 1);
        }
    }
}

//        1. Call(3)
//        │
//        ├── 2. Call(1)          // 3 → n-2
//        │   │
//        │   ├── 3. Call(-1)     // 1 → n-2
//        │   │   └── 4. Print(-1)
//        │   │
//        │   ├── 5. Print(1)
//        │   │
//        │   └── 6. Call(0)      // 1 → n-1
//        │       └── 7. Print(0)
//        │
//        ├── 8. Print(3)
//        │
//        └── 9. Call(2)          // 3 → n-1
//            │
//            ├── 10. Call(0)     // 2 → n-2
//            │    └── 11. Print(0)
//            │
//            ├── 12. Print(2)
//            │
//            └── 13. Call(1)     // 2 → n-1
//                 │
//                 ├── 14. Call(-1)
//                 │    └── 15. Print(-1)
//                 │
//                 ├── 16. Print(1)
//                 │
//                 └── 17. Call(0)
//                     └── 18. Print(0)
