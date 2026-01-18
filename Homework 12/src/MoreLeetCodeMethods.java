import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class MoreLeetCodeMethods {

    //https://leetcode.com/problems/design-hashmap/description/
    class MyHashMap<E> {

        static class MyEntry<E> {
            int key;
            int value;
            int hashCode;

            MyEntry(int key, int value, int hashCode) {
                this.key = key;
                this.value = value;
                this.hashCode = hashCode;
            }
        }

        ArrayList<MyEntry>[] table;
        double loadFactor;
        int occupants;

        public MyHashMap(double LoadFactor) {
            table = new ArrayList[5];

            for (int i = 0; i < table.length; i++) {
                table[i] = new ArrayList<>();
            }

            this.loadFactor = LoadFactor;
        }

        public MyHashMap() {

            table = new ArrayList[5];

            for (int i = 0; i < table.length; i++) {
                table[i] = new ArrayList<>();
            }

            this.loadFactor = 0.75;
        }

        public void put(int key, int value) {

            int hashValue = Math.abs(Objects.hash(key));
            int index = hashValue % table.length;
            for (MyEntry entry : table[index]) {
                if (entry.key == key) {
                    entry.value = value;
                    entry.hashCode = hashValue;
                    return;
                }
            }

            table[index].add(new MyEntry(key, value, hashValue));
            occupants++;

            if ((double) occupants / table.length > loadFactor) {
                ArrayList<MyEntry>[] temp = new ArrayList[(table.length * 2) + 1];

                for (int i = 0; i < temp.length; i++) {
                    temp[i] = new ArrayList<>();
                }

                for (int i = 0; i < table.length; i++) {
                    for (MyEntry entry : table[i]) {
                        int newIndex = entry.hashCode % temp.length;
                        temp[newIndex].add(entry);
                    }
                }

                //new table, becomes superb table.
                table = temp;
            }
        }

        public int get(int key) {

            int hashValue = Math.abs(Objects.hash(key));
            int index = hashValue % table.length;

            for (MyEntry entry : table[index]) {
                if (entry.key == key) {
                    return entry.value;
                }
            }
            return -1;
        }

        public void remove(int key) {
            int hashValue = Math.abs(Objects.hash(key));
            int index = hashValue % table.length;

            ArrayList<MyEntry> swashBucket = table[index];

            for (int i = 0; i < swashBucket.size(); i++) {
                if (swashBucket.get(i).key == key) {
                    table[index].remove(i);
                    occupants--;
                    return;
                }
            }
        }
        /**
         * Your MyHashMap object will be instantiated and called as such:
         * MyHashMap obj = new MyHashMap();
         * obj.put(key,value);
         * int param_2 = obj.get(key);
         * obj.remove(key);
         */
    }
}