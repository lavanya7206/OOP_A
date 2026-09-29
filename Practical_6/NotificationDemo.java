@FunctionalInterface
interface Notifier 
{
    void send(String message);
}
interface Urgent 
{
}
class EmailSender implements Urgent
{
    static Notifier create()
    {
        return message -> System.out.println("Email: " + message);
    }
}
class SMSSender
{
    static Notifier create()
    {
        return message -> System.out.println("SMS: " + message);
    }
}
class UrgentEmailSender implements Urgent
{
    static Notifier create()
    {
        return message -> System.out.println("Urgent Email: " + message);
    }
}
public class NotificationDemo
{
    public static void main(String[] args) 
    {
        Notifier[] senders = 
        {
            EmailSender.create(),
            SMSSender.create(),
            UrgentEmailSender.create()
        };
        String message = "Meeting at 10 AM";
        System.out.println("Normal Broadcast:");
        for (Notifier n : senders)
        {
            n.send(message);
        }
        System.out.println();
        System.out.println("Urgent Broadcast:");
        for (Notifier n : senders)
            {
            if (n instanceof Urgent)
            {
                n.send("URGENT: " + message);
            }
        }
    }
}
