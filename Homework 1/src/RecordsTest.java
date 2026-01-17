package Homework_1;

public class RecordsTest {
    void main() {
        Name person = new Name("Penny", "Blake");
        PhoneNumber whatsapp = new PhoneNumber("956", "623", "5535");

        System.out.println(person);
        System.out.println(whatsapp);

        double start = 12.45, finish = 11.25;

        Interval interval;

        try {
            interval = new Interval(start, finish);
        } catch (IllegalArgumentException e) {
            interval = new Interval(finish, start);
        }
        System.out.println(interval.contains(new Interval(start - 2, finish)));
        System.out.println(interval);
    }
}

record Name(String firstName, String lastName) {
    public String toString() {
        return firstName + " " + lastName;
    }
}

record PhoneNumber(String areaCode, String prefix, String lineNumber) {
        public String toString() {
        return "+1 " + areaCode + "-" + prefix + "-" + lineNumber;
    }
}

record PhonebookEntry(Name name, PhoneNumber number) {}

record Interval(double min, double max) {
    Interval(double min, double max) {
        this.min = min;
        this.max = max;

        if (min > max) throw new IllegalArgumentException();
    }

    public double length() { return max - min; }
    public boolean contains(double x) { return (x == max || x == min); }
    public boolean contains(Interval other) { return (other.min <= min && other.max >= max); }
    public boolean intersects(Interval other) { return (other.min <= min || other.max >= max); }
    public String toString() { return "[" + min + ", " + max + "]"; }
}
