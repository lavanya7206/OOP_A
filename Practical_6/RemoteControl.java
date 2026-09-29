interface Switchable 
{
    void on();
    void off();
    default void toggle() 
    {
        System.out.println("Toggling device");
        on();
    }
}
class Fan implements Switchable 
{
    public void on() 
    {
        System.out.println("Fan is ON");
    }
    public void off()
    {
        System.out.println("Fan is OFF");
    }
}
class Light implements Switchable 
{
    public void on()
    {
        System.out.println("Light is ON");
    }
    public void off()
    {
        System.out.println("Light is OFF");
    }
}
@FunctionalInterface
interface SwitchPermission 
{
    boolean maySwitchOn(Switchable device, int hour);
}
public class RemoteDemo 
{
    public static void main(String[] args) 
    {
        Switchable[] devices =
        {
            new Fan(),
            new Light()
        };
        System.out.println("Toggling devices:");
        for (Switchable d : devices) 
        {
            d.toggle();
        }
        SwitchPermission permission1 = new SwitchPermission() 
        {
            public boolean maySwitchOn(Switchable device, int hour) 
            {
                return hour >= 8 && hour <= 22;
            }
        };
        SwitchPermission permission2 =(device, hour) -> hour >= 6 && hour <= 23;
        System.out.println();
        System.out.println("Anonymous class: " +permission1.maySwitchOn(devices[0], 7));
        System.out.println("Lambda: " +permission2.maySwitchOn(devices[1], 7));
    }
}
