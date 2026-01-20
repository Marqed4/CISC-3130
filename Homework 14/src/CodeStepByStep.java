import com.sun.source.tree.Tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Map;
import java.io.File;

public class CodeStepByStep {

    //notes:
    //Names must appear in alphabetical order...

    public static String biggestFamily(String fileInput) throws IOException {
        Scanner scan = new Scanner(new File(fileInput));
        TreeMap<String, Integer> fam = new TreeMap<>();

        while (scan.hasNextLine()) {
            String person = scan.nextLine();
            String familyName = person.substring
                    (person.indexOf(" ") + 1);

            fam.put(familyName, fam.getOrDefault(familyName, 0) + 1);
        }

        ArrayList<String> largestFamList = new ArrayList<>();

        int largestFamCount = 0;
        for (Map.Entry<String, Integer> entry : fam.entrySet()) {
            if (entry.getValue() > largestFamCount) {
                largestFamCount = entry.getValue();
            }
        }

        for (Map.Entry<String, Integer> entry : fam.entrySet()) {
            if (entry.getValue() == largestFamCount) {
                largestFamList.add(entry.getKey());
            }
        }

        StringBuilder largestFamPrint = new StringBuilder();
        int index = 0;

        while (!largestFamList.isEmpty()) {

            largestFamPrint.append(largestFamList.get(index) + " family: ");

            scan = new Scanner(new File(fileInput));

            while (scan.hasNextLine()) {
                String person = scan.nextLine();
                if (largestFamList.get(0).equals((person.substring
                        (person.indexOf(" ") + 1)))) {

                    largestFamPrint.append(person.substring(0, person.indexOf(" ")) + " ");
                }

                if (!scan.hasNextLine()) {
                    System.out.println(largestFamPrint);
                    largestFamPrint = new StringBuilder();
                    largestFamList.remove(0);
                }
            }
        }
        scan.close();
        return "";
    }

    void main() throws IOException {
        biggestFamily("C:\\Users\\Derpe\\IdeaProjects\\3130\\Homework 14\\src\\WinterIsAlreadyHere.txt");
    }
}
