import java.util.*;

/**
 * A phone number, such as 718-951-0430 consists of an area code (718),
 * a prefix (951), and a line number (0430).
 */

record Name(String first, String last) {
    @Override
    public String toString() {
        return first + " " + last;
    }
}

record PhoneNumber(String areaCode, String prefix, String lineNumber) {
    public String toString() {
        return "+1 " + areaCode + "-" + prefix + "-" + lineNumber;
    }
}

public class Phonebook {

    private final Map<Name, Set<PhoneNumber>> map = new HashMap<>();

    // associates the given phoneNumber with the given name;
    // the name might already exist in the phonebook
    public void add(Name name, PhoneNumber phoneNumber) {
        if (map.containsKey(name)) {
            map.get(name).add(phoneNumber);
        } else {
            map.put(name, Set.of(phoneNumber));
        }
    }

    public void remove(Name name) {
        map.remove(name);
    }

    // removes the given phoneNumber for the given name
    public void remove(Name name, PhoneNumber phoneNumber) {
        if (map.containsKey(name)) {
            map.get(name).remove(phoneNumber);
        }
    }

    public Set<PhoneNumber> lookup(Name name) {
        return map.get(name);
    }

    public Set<Name> reverseLookup(PhoneNumber phoneNumber) {
        Set<Name> matchingNames = new HashSet<>();

        for (Map.Entry<Name, Set<PhoneNumber>> entries : map.entrySet()) {
//                if (Objects.equals(phoneNumber, map.get(entries))) {
            if (entries.getValue().contains(phoneNumber)) {
                matchingNames.add(entries.getKey());
            }
        }
        return matchingNames;
    }

    public Set<Name> names() {

        Set<Name> names = new HashSet<>();

        for (Map.Entry<Name, Set<PhoneNumber>> entrties : map.entrySet()) {
            names.add(entrties.getKey());
        }

        return names;
    }

    public Set<PhoneNumber> phoneNumbers() {

        Set<PhoneNumber> numbers = new HashSet<>();

        for (Map.Entry<Name, Set<PhoneNumber>> entrties : map.entrySet()) {
            for (PhoneNumber phoneNumber : entrties.getValue()) {
                numbers.add(phoneNumber);
            }
        }

        return numbers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[PhoneBook]" + '\n');

        for (Map.Entry<Name, Set<PhoneNumber>> entry : map.entrySet()) {

            sb.append("[" + entry.getKey() + ": " + entry.getValue().toString() + "]" + "\n");
        }
        return sb.toString();
    }

    // returns the maximum number of phone numbers per name
    public int maxPhoneNumbers() {

        int max = 0;
        for (Map.Entry<Name, Set<PhoneNumber>> entry : map.entrySet()) {
            if (entry.getValue().size() > max) {
                max = entry.getValue().size();
            }
        }
        return max;
    }
}

class Demo {
    public static void main(String[] args) {

        //the phonebook of a dishonest man.

        Phonebook People = new Phonebook();

        Name Jack = new Name("Jackson Jr.", "Lamport");
        PhoneNumber JacksPhone = new PhoneNumber("347", "233", "4565");

        People.add(Jack, JacksPhone);

        Name JacksWife = new Name("Trina", "Lamport");
        PhoneNumber JacksWifePhone = new PhoneNumber("347", "233", "4566");

        People.add(JacksWife, JacksWifePhone);

        Name JacksDad = new Name("Jackson Sr.", "Lamport");
        PhoneNumber JacksDadPhone = new PhoneNumber("347", "233", "4567");

        People.add(JacksDad, JacksDadPhone);

        Name SisterInLaw = new Name("Harper", "L'Overture");
        PhoneNumber SisterInLawPhone = new PhoneNumber("646", "555", "4402");

        People.add(SisterInLaw, SisterInLawPhone);

        Name Accountant = new Name("Bishop", "Renter");
        PhoneNumber AccountantPhone = new PhoneNumber("212", "555", "1201");

        People.add(Accountant, AccountantPhone);

        Name Dentist = new Name("Carl", "Tracy");
        PhoneNumber DentistPhone = new PhoneNumber("212", "555", "1202");

        People.add(Dentist, DentistPhone);

        Name Plumber = new Name("Richmond", "Francis");
        PhoneNumber PlumberPhone = new PhoneNumber("718", "555", "3301");

        People.add(Plumber, PlumberPhone);

        Name Assistant = new Name("Maddy", "Becker");
        PhoneNumber AssistantPhone = new PhoneNumber("718", "555", "3302");

        People.add(Assistant, AssistantPhone);

        Name Driver = new Name("Jeremiah", "Bedford");
        PhoneNumber DriverPhone = new PhoneNumber("646", "555", "4401");

        People.add(Driver, DriverPhone);

        Name Technician = new Name("Technician", "On-Call");
        PhoneNumber TechnicianPhone = new PhoneNumber("646", "555", "4402");

        People.add(Technician, TechnicianPhone);

        Name Wife = new Name("Francesca", "Melbourne");
        PhoneNumber WifePhone = new PhoneNumber("917", "555", "5501");

        People.add(Wife, WifePhone);

        Name Dad = new Name("Travis", "Melbourne");
        PhoneNumber DadPhone = new PhoneNumber("917", "555", "5502");

        People.add(Dad, DadPhone);

        Name Mother = new Name("Xiomara", "Melbourne");
        PhoneNumber MotherPhone = new PhoneNumber("917", "555", "5503");

        People.add(Mother, MotherPhone);

        System.out.println(People.names());
        System.out.println(People.phoneNumbers());

        System.out.println(People);
    }
}
