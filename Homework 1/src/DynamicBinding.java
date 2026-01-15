public class DynamicBinding {}

class A extends B {
    public void method1() { IO.print("a 1 "); }
}

class B extends C {
    public String toString() { return "b"; }

    public void method2() {
        IO.print("b 2 ");
        method1();
    }
}

class C {
    public String toString() { return "c"; }

    public void method1() { IO.print("c 1 "); }

    public void method2() { IO.print("c 2 "); }
}

class D extends B {
    public void method2() {
        IO.print("d 2 ");
        method1();
    }
}

class PolymorphismMystery1 {
    void main() {
        C[] elements = {new A(), new B(), new C(), new D()};

        for (C element : elements) {
            IO.print(element + " ");
            element.method1();
            element.method2();
            IO.println();
        }
    }
}

interface MyIterator {
    boolean hasNext();
    int next();
}

interface MyCollection {
    int size();
    boolean isEmpty();
    MyIterator iterator();
}

interface MyList extends MyCollection {}

abstract class MyAbstractCollection implements MyCollection {
    @Override
    public boolean isEmpty() {
        IO.println("MyAbstractCollection.isEmpty");
        return size() == 0;
    }

    @Override
    public String toString() {
        IO.println("MyAbstractCollection.toString");
        MyIterator iterator = iterator();
        String result = "[";

        while (iterator.hasNext()) {
            result += iterator.next();
        }

        return result + "]";
    }
}

abstract class MyAbstractList extends MyAbstractCollection implements MyList {
    @Override
    public MyIterator iterator() {
        IO.println("MyAbstractList.iterator");
        return new MyListIterator();
    }
}

class MyListIterator implements MyIterator {
    @Override
    public boolean hasNext() {
        IO.println("MyListIterator.hasNext");
        return false;
    }

    @Override
    public int next() {
        IO.println("MyListIterator.next");
        return 0;
    }
}

class MyArrayList extends MyAbstractList {
    @Override
    public int size() {
        IO.println("MyArrayList.size");
        return 0;
    }
}

class PolymorphismMystery2 {
    void main() {
        MyList list = new MyArrayList();
        IO.println(list.isEmpty());
        IO.println(list);
    }
}