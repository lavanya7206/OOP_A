class TicketBooking 
{
    int seatsLeft = 5;
    synchronized void book(String name) 
    {
        if (seatsLeft > 0) 
        {
            System.out.println(name + " booked a seat.");
            seatsLeft--;
        } else 
        {
            System.out.println(name + " could not book a seat.");
        }
    }
}
class Customer extends Thread 
{
    TicketBooking booking;
    Customer(TicketBooking booking) 
    {
        this.booking = booking;
    }
    public void run() 
    {
        booking.book(Thread.currentThread().getName());
    }
}
public class Main1 
{
    public static void main(String[] args) throws Exception 
    {
        TicketBooking booking = new TicketBooking();
        Customer[] customers = new Customer[10];
        for (int i = 0; i < 10; i++) 
        {
            customers[i] = new Customer(booking);
            customers[i].setName("Customer-" + (i + 1));
            customers[i].start();
        }
        for (int i = 0; i < 10; i++) 
        {
            customers[i].join();
        }
        System.out.println("Seats left = " + booking.seatsLeft);
    }
}