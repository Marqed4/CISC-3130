import java.io.IOException;
import java.util.*;
import java.io.File;

public class CodeStepByStep {

    //notes:
    //Names must appear in alphabetical order...
    //Can't open file more than once.
    //Only two aux data structs allowed.

    //Thoughts:
    //How can we make this better?
    //TreeMap iterates from A-Z automatically.
    //Pull from bottom of the TreeSet instead of searching Map. (NO, this is not # only TreeMap)
    //Trying switching instead. From <String, Integer> do, <Integer, String>? (No, more than one family can't have the same count)


    public static String biggestFamily(String fileInput) throws IOException {
        Scanner scan = new Scanner(new File(fileInput));

        TreeMap<String, Integer> fam = new TreeMap<>();
        List<String> famMembers = new LinkedList<>();

        while (scan.hasNextLine()) {
            String person = scan.nextLine();
            famMembers.add(person);

            person = person.substring(person.indexOf(" ") + 1);
            fam.put(person, fam.getOrDefault(person, 0) + 1);
        }

        scan.close();
        Collections.sort(famMembers);

        //Find the largest family
        int largestFamCount = 0;
        for (Map.Entry<String, Integer> entry : fam.entrySet()) {
            if (entry.getValue() > largestFamCount) {
                largestFamCount = entry.getValue();
            }
        }

        for (Map.Entry<String, Integer> entry : fam.entrySet()) {
            if (entry.getValue() == largestFamCount) {
                String familyName = entry.getKey();

                System.out.print(familyName + " family: ");

                //Create an iterable, that can be question to decide when the next new-line is appropriate
                Iterator<String> iter = fam.keySet().iterator();
                for (String person : famMembers) {

                    //Take advantage of substring to section the name into valuable pieces
                    String firstName = person.substring(0, person.indexOf(" "));
                    String lastName = (person.substring(person.indexOf(" ") + 1));

                    //Compare/print people with the CURRENTLY INDEXED family name
                    if (familyName.equals(lastName)) {
                        if (iter.hasNext()) {
                            System.out.print(firstName + " ");
                        } else {
                            System.out.print(firstName);
                        }
                    }
                }
                System.out.println();
            }
        }
        return "";
    }

    void main() throws IOException {
        biggestFamily("C:\\Users\\Derpe\\IdeaProjects\\3130\\Homework 14\\src\\WinterIsAlreadyHere.txt");
    }
}
