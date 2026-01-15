import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class GenericStaticMethods {

    //1
    static <E> int count(E[] elmts, E elmt) {
        int n = 0;
        for (E e : elmts) {
            if (Objects.equals(e, elmt)) n++;
        }
        return n;
    }

    //2
    static <E extends Number> double average(E[] elmts) {

        if (elmts.length == 0) throw new IllegalArgumentException();

        double n = 0;
        for (E e : elmts) {
            n += e.doubleValue();
        }
        return n / elmts.length;
    }

    //3
    static <E extends Comparable<E>> boolean isSorted(E[] elmts) {
        for (int i = 0; i < elmts.length - 1; i++) {
            if (elmts[i].compareTo(elmts[i + 1]) > 0) return false;
        }
        return true;
    }

    //4
    static <E extends Comparable<E>> void sort(E[] elmts) {
        if (elmts.length <= 1) {
            System.out.println("Seriously... Why?");
            return;
        }
        ;

        E[] left = Arrays.copyOfRange(elmts, 0, elmts.length / 2);
        E[] right = Arrays.copyOfRange(elmts, elmts.length / 2, elmts.length);

        sort(left);
        sort(right);

        merge(left, right, elmts);
    }

    private static <E extends Comparable<E>> void merge(E[] arr1, E[] arr2, E[] result) {

        int index1 = 0, index2 = 0, resultIndex = 0;

        while (index1 < arr1.length && index2 < arr2.length) {
            if (arr1[index1].compareTo(arr2[index2]) < 0) {
                result[resultIndex++] = arr1[index1++];
            } else {
                result[resultIndex++] = arr2[index2++];
            }
        }

        while (index1 < arr1.length) {
            result[resultIndex++] = arr1[index1++];
        }

        while (index2 < arr2.length) {
            result[resultIndex++] = arr2[index2++];
        }
    }

    //1
    static class TimeSpan implements Comparable<TimeSpan> {
        private final int minutes;

        private TimeSpan(int minutes) {
            this.minutes = minutes;
        }

        private static TimeSpan ofHours(int hours) {
            if (hours < 0) throw new IllegalArgumentException("irrepresentable timespan");
            return new TimeSpan(hours * 60);
        }

        public static TimeSpan ofMinutes(int totalMinutes) {
            if (totalMinutes < 0) throw new IllegalArgumentException("irrepresentable timespan");
            return new TimeSpan(totalMinutes);
        }

        public static TimeSpan ofHoursAndMinutes(int hours, int minutes) {
            if (hours < 0 || minutes < 0)
                throw new IllegalArgumentException("irrepresentable timespan");

//        return ofHours(hours) + ofMinutes(minutes);
            return new TimeSpan(hours * 60 + minutes);
        }

        public int getHours() {
            return minutes / 60;
        }

        public int getMinutes() {
            return minutes % 60;
        }

        public int getTotalMinutes() {
            return minutes;
        }

        public String toString() {
            return String.format("%02d:%02d", getHours(), getMinutes());
        }

        public boolean equals(Object o) {
            return o instanceof TimeSpan other &&
                    getTotalMinutes() == other.getTotalMinutes();
        }

        public int hashCode() {
            return Objects.hash(getHours(), getMinutes());
        }

        public TimeSpan plus(TimeSpan other) {
            return new TimeSpan(getTotalMinutes() + other.getTotalMinutes());
        }

        public TimeSpan plusHours(int hours) {
            if (hours < 0) throw new IllegalArgumentException("irrepresentable timespan");
            return new TimeSpan(getTotalMinutes() + (hours * 60));
        }

        public TimeSpan plusMinutes(int minutes) {
            if (minutes < 0) throw new IllegalArgumentException("irrepresentable timespan");
            return new TimeSpan(getTotalMinutes() + minutes);
        }

        public TimeSpan plusHoursAndMinutes(int hours, int minutes) {
            if (hours < 0 || minutes < 0) throw new IllegalArgumentException("irrepresentable timespan");
            return new TimeSpan(getTotalMinutes() + (hours * 60) + minutes);
        }

        @Override
        public int compareTo(TimeSpan o) {
            if (this.minutes < o.minutes) return -1;
            else if (this.minutes > o.minutes) return 1;
            else return 0;
        }
    }

    //2
    static record Book(String title, String author, int publicationYear, double price) implements Comparable<Book> {
        public Book(String title, String author, int publicationYear, double price) {
            this.title = Objects.requireNonNull(title);
            this.author = Objects.requireNonNull(author);
            this.publicationYear = publicationYear;
            this.price = price;
        }

        @Override
        public int compareTo(Book o) {
            if (this.publicationYear < o.publicationYear) return -1;
            else if (this.publicationYear > o.publicationYear) return 1;
            else if (this.price < o.price) return -1;
            else if (this.price > o.price) return 1;
            else if (this.author.compareTo(o.author) < 0) return -1;
            else if (this.author.compareTo(o.author) > 0) return 1;
            else if (this.title.compareTo(o.title) < 0) return -1;
            else if (this.title.compareTo(o.title) > 0) return 1;
            else return 0;
        }
    }

    record Interval(double min, double max) {
        Interval(double min, double max) {
            this.min = min;
            this.max = max;

            if (min > max) throw new IllegalArgumentException();
        }

        static Comparator<Interval> compareByLength = new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if ((o1.max - o1.min) < (o2.max - o2.min)) {
                    return -1;
                } else if ((o1.max - o1.min) > (o2.max - o2.min)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        static Comparator<Interval> compareByMinThenMax = new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if ((o1.min) < (o2.min)) {
                    return -1;
                } else if ((o1.min) > (o2.min)) {
                    return 1;
                } else if ((o1.max) < (o2.max)) {
                    return -1;
                } else if ((o1.max) > (o2.max)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        static Comparator<Interval> compareByMaxThenMin = new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if ((o1.max) < (o2.max)) {
                    return -1;
                } else if ((o1.max) > (o2.max)) {
                    return 1;
                } else if ((o1.min) < (o2.min)) {
                    return -1;
                } else if ((o1.min) > (o2.min)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
    }
}