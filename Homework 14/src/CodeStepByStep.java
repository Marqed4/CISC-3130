import java.io.IOException;
import java.util.*;
import java.io.File;

public class CodeStepByStep {

    //notes:
    //Names must appear in alphabetical order...
    //Can't open file more than once.

    public static String biggestFamily(String fileInput) throws IOException {
        Scanner scan = new Scanner(new File(fileInput));
        TreeMap<String, Integer> fam = new TreeMap<>();
        List<String> largestFamList = new LinkedList<>();
        List<String> famMembers = new LinkedList<>();

        while (scan.hasNextLine()) {
            String person = scan.nextLine();
            famMembers.add(person);

            fam.put(person.substring
                    (person.indexOf(" ") + 1), fam.getOrDefault(person.substring
                    (person.indexOf(" ") + 1), 0) + 1);
        }

        scan.close();

        Collections.sort(famMembers);
        Queue<String> peopleFromFile = new LinkedList<>(famMembers);

        //find the largest family
        int largestFamCount = 0;
        for (Map.Entry<String, Integer> entry : fam.entrySet()) {
            if (entry.getValue() > largestFamCount) {
                largestFamCount = entry.getValue();
            }
        }

        //add largest families
        for (Map.Entry<String, Integer> entry : fam.entrySet()) {
            if (entry.getValue() == largestFamCount) {
                largestFamList.add(entry.getKey());
            }
        }

        //run through list of largest families
        while (!largestFamList.isEmpty()) {

            System.out.print(largestFamList.get(0) + " family: ");
            int index2 = peopleFromFile.size();

            //run through list of available names
            while (index2 > 0) {
                index2--;

                //remove/add people from queue to preserve data struct
                String person = peopleFromFile.remove();
                peopleFromFile.add(person);

                //compare & print those with the CURRENTLY INDEXED family name
                if (largestFamList.get(0).equals((person.substring(person.indexOf(" ") + 1)))) {
                    if (!(index2 - 1 < 0)) {
                        System.out.print(person.substring(0, person.indexOf(" ")) + " ");
                    } else {
                        System.out.print(person.substring(0, person.indexOf(" ")));
                    }
                }
            }
            largestFamList.remove(0);
            System.out.println();
        }
        return "";
    }

    void main() throws IOException {
        biggestFamily("C:\\Users\\Derpe\\IdeaProjects\\3130\\Homework 14\\src\\WinterIsAlreadyHere.txt");
    }
}
