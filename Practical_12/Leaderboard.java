import java.util.*;
public class Leaderboard 
{
    public static void main(String[] args)
    {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Amit", 90);
        scores.put("Riya", 95);
        scores.put("Rahul", 85);
        scores.put("Neha", 95);
        scores.put("Karan", 80);
        List<String> players = new ArrayList<>(scores.keySet());
        Collections.sort(players, new Comparator<String>()
        {
            public int compare(String a, String b)
            {
                if (!scores.get(a).equals(scores.get(b)))
                {
                    return scores.get(b) - scores.get(a);
                }
                return a.compareTo(b);
            }
        });
        int N = 3;
        System.out.println("Top " + N + " players:");
        for (int i = 0; i < N && i < players.size(); i++)
        {
            String name = players.get(i);
            System.out.println(name + " : " + scores.get(name));
        }
    }
}