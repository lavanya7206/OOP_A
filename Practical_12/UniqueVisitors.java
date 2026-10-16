import java.util.*;
public class UniqueVisitors
{
    public static void main(String[] args)
    {
        String[] users =
        {
            "Amit", "Riya", "Amit", "Rahul",
            "Neha", "Riya", "Karan"
        };
        HashSet<String> set = new HashSet<>();
        LinkedHashSet<String> orderedSet = new LinkedHashSet<>();
        for (String user : users)
        {
            set.add(user);
            orderedSet.add(user);
        }
        System.out.println("Unique visitors: " + set.size());
        System.out.println("First three unique visitors:");
        int count = 0;
        for (String user : orderedSet)
        {
            if (count == 3)
            {
                break;
            }
            System.out.println(user);
            count++;
        }
    }
}