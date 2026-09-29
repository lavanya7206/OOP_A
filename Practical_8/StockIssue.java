
class OutOfStockException extends Exception
{
    int shortfall;

    OutOfStockException(String msg, int shortfall)
    {
        super(msg);
        this.shortfall = shortfall;
    }
}

class InvalidQuantityException extends Exception
{
    InvalidQuantityException(String msg)
    {
        super(msg);
    }
}

class Warehouse
{
    int stock = 10;

    void issue(String item, int qty)
        throws OutOfStockException, InvalidQuantityException
    {
        if (qty <= 0)
            throw new InvalidQuantityException("Invalid quantity: " + qty);

        if (qty > stock)
        {
            int shortfall = qty - stock;
            throw new OutOfStockException(
                "Not enough stock for " + item +
                ". Shortfall = " + shortfall,
                shortfall
            );
        }

        stock -= qty;
        System.out.println(qty + " " + item + " issued successfully.");
    }
}

public class StockIssue
{
    public static void main(String[] args)
    {
        Warehouse w = new Warehouse();

        String[] items = {"Pen", "Book", "Bag", "Pencil"};
        int[] quantities = {5, 8, 0, 3};

        for (int i = 0; i < quantities.length; i++)
        {
            try
            {
                w.issue(items[i], quantities[i]);
            }
            catch (OutOfStockException e)
            {
                System.out.println("Stock Error: " + e.getMessage());
            }
            catch (InvalidQuantityException e)
            {
                System.out.println("Quantity Error: " + e.getMessage());
            }
        }

        System.out.println("Processing completed.");
    }
}