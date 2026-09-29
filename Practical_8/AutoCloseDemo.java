class FileResource implements AutoCloseable
{
    public void open()
    {
        System.out.println("Resource opened");
    }

    public void read()
    {
        System.out.println("Reading resource...");
        throw new RuntimeException("Error while reading resource");
    }

    public void close()
    {
        System.out.println("Resource closed");
    }
}

public class AutoCloseDemo
{
    public static void main(String[] args)
    {
        try (FileResource resource = new FileResource())
        {
            resource.open();
            resource.read();
        }
        catch (Exception e)
        {
            System.out.println("Original error: " + e.getMessage());
        }
    }
}
