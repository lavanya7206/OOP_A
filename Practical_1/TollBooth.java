import java.util.Scanner;
record Vehicle(String number, String type) {}
public class TollBooth
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int totalToll = 0;
        int bikeCount = 0;
        int carCount = 0;
        int truckCount = 0;
        while (true)
        {
            System.out.print("Enter Vehicle Number (or done to stop): ");
            String number = sc.next();
            if (number.equalsIgnoreCase("done"))
            {
                break;
            }
            System.out.print("Enter Vehicle Type (bike/car/truck): ");
            String type = sc.next().toLowerCase();
            Vehicle v = new Vehicle(number, type);
            int toll = switch (v.type())
            {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> 0;
            };
            totalToll = totalToll + toll;
            if (v.type().equals("bike"))
            {
                bikeCount++;
            }
            else if (v.type().equals("car"))
            {
                carCount++;
            }
            else if (v.type().equals("truck"))
            {
                truckCount++;
            }
        }
        System.out.println("Total Toll = " + totalToll);
        if (bikeCount >= carCount && bikeCount >= truckCount)
        {
            System.out.println("Most Frequent: bike");
        }
        else if (carCount >= bikeCount && carCount >= truckCount)
        {
            System.out.println("Most Frequent: car");
        }
        else
        {
            System.out.println("Most Frequent: truck");
        }
        sc.close();
    }
}
