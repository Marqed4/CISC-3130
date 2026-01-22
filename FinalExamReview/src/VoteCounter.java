import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class VoteCounter {
    Map<String, Integer> polls = new HashMap<>();

    public void count(Collection<String> votes) {
        for (String name : votes) {
            if (!polls.containsKey(name)) {
                polls.put(name, polls.getOrDefault(name, 0) + 1);
            } else {
                polls.put(name, polls.get(name) + 1);
            }
        }
    }

    public int getCount(String candidate) {
        if (polls.containsKey(candidate)) {
            return polls.get(candidate);
        }

        return 0;
    }

    public SortedSet<String> getCandidates() {
        //TreeSet will automatically sort it via natural order
        return new TreeSet<>(polls.keySet());
    }

    public String getWinner() {
//        return polls.entrySet().stream().max(Map.Entry.comparingByValue());
        int mostSeenVotes = 0;
        String bestCandidate = null;

        for (String name : polls.keySet()) {
            int candidatesVotes = getCount(name);
            if ( candidatesVotes > mostSeenVotes ) {
                mostSeenVotes = candidatesVotes;
                bestCandidate = name;
            }
        }

        return bestCandidate;
    }
}

class Test1 {
    void main() throws FileNotFoundException {

        //C:\Users\Derpe\IdeaProjects\3130\FinalExamReview\FinalExamFolder2025\2024 U.S. Presidential Election 1 to 10,000.txt

        List<String> votes = new ArrayList<>();
        Scanner filename = new Scanner(System.in);
        String file_address = filename.nextLine();
        VoteCounter summation_machine = new VoteCounter();
        Scanner file_scan = new Scanner(new File(file_address));

        while (file_scan.hasNextLine()) {
            votes.add(file_scan.nextLine());
        }

        summation_machine.count(votes);
        System.out.println(summation_machine.getWinner());
    }
}