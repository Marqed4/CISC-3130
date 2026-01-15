import java.util.List;

public class EqualsHashCode {}

class Engine {
    String type;
    int horsepower;

    // TODO - equals and hashCode
    public boolean equals(Object x) {
        return x instanceof Engine other && java.util.Objects.equals(this.type, other.type) && java.util.Objects.equals(this.horsepower, other.horsepower);
    }

    public int hashCode() {
        return java.util.Objects.hash(type, horsepower);
    }
}

class Car {
    String make, model;
    Engine engine;

    // TODO - equals and hashCode
    public boolean equals(Object x) {
        return x instanceof Car other && java.util.Objects.equals(this.make, other.make) && java.util.Objects.equals(this.model, other.model) && java.util.Objects.equals(this.engine, other.engine);
    }

    public int hashCode() {
        return java.util.Objects.hash(make, model, engine);
    }
}

class Person {
    String name;
    int age;

    // TODO - equals and hashCode
    public boolean equals(Object x) {
        return x instanceof Person other && java.util.Objects.equals(this.name, other.name) && java.util.Objects.equals(this.age, other.age);
    }

    public int hashCode() {
        return java.util.Objects.hash(name, age);
    }
}

class Owner {
    Person person;
    List<Car> cars;

    // TODO - equals and hashCode
    public boolean equals(Object x) {
        return x instanceof Owner other && java.util.Objects.equals(this.person, other.person) && java.util.Objects.equals(this.cars, other.cars);
    }

    public int hashCode() {
        return java.util.Objects.hash(person, cars);
    }
}

