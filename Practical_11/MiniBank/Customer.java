import java.io.Serializable;
public class Customer implements Serializable 
{
    private static final long serialVersionUID = 1L;
    String name;
    int customerId;
    public Customer(String name, int customerId) 
    {
        this.name = name;
        this.customerId = customerId;
    }
    public String toString() 
    {
        return "Customer ID: " + customerId +", Name: " + name;
    }
}
