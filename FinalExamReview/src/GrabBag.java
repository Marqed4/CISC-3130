import java.util.*;

public class GrabBag<E> { //implements GrabBag<E>
    List<E> list;
    int size = 0;
    Random rand = new Random();

    public GrabBag() {
        list = new ArrayList<>();
    }

    public void insert(E e) {
        list.add(e);
        size++;
    }

    public E sample() {

        E entity = remove();
        insert(entity);

        return entity;
    }

    public E remove() {
        if (isEmpty()) throw new NoSuchElementException();

        int index = rand.nextInt(size);
        E entity = list.get(index);

        if (index != size - 1) {
            list.set(index, list.removeLast());
        } else {
            list.remove(index);
        }

        size--;
        return entity;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

class Test2 {
    void main() {
        record Popsicle(String flavor) {
            public String toString() {
                if (flavor.equals("Squid")) {
                    return flavor() + " is a questionable flavor";
                }
                return flavor() + " is a good flavor for a popsicle";
            }
        }

        GrabBag<Popsicle> grabBag = new GrabBag<>();
        grabBag.insert(new Popsicle("Vanilla"));
        grabBag.insert(new Popsicle("Strawberry"));
        grabBag.insert(new Popsicle("Bubblegum"));
        grabBag.insert(new Popsicle("Squid"));
        grabBag.insert(new Popsicle("Raspberry"));

        while (!grabBag.isEmpty()) {
            System.out.println(grabBag.remove());
        }
    }
}
