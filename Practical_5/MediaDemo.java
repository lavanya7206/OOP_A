abstract class Media 
{
    String title;
    Media(String title) 
    {
        this.title = title;
    }
    abstract double lateFee(int daysLate);
}
class Book extends Media 
{
    Book(String title) 
    {
        super(title);
    }
    double lateFee(int daysLate) 
    {
        return daysLate * 5;
    }
}
class DVD extends Media
{
    DVD(String title) 
    {
        super(title);
    }
    double lateFee(int daysLate) 
    {
        return daysLate * 10;
    }
}
class Magazine extends Media 
{
    Magazine(String title) 
    {
        super(title);
    }
    double lateFee(int daysLate) 
    {
        return daysLate * 3;
    }
}
public class MediaDemo {
    public static void main(String[] args) 
    {
        Media[] items = {
            new Book("Java Basics"),
            new DVD("Inception"),
            new Magazine("Tech World"),
            new Book("Data Structures")
        };
        int[] lateDays = {2, 3, 4, 1};
        double totalFee = 0;
        for (int i = 0; i < items.length; i++) 
        {
            double fee = items[i].lateFee(lateDays[i]);
            System.out.println(items[i].title + " Late Fee = ₹" + fee);
            totalFee = totalFee + fee;
        }
        System.out.println("Total Late Fee = ₹" + totalFee);
    }
}
