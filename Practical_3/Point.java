public class Point
 {
    private int x;
    private int y;
    public Point(int x, int y) 
{
        this.x = x;
        this.y = y;
    }
    public String toString() 
{
        return "(" + x + ", " + y + ")";
    }
    public boolean equals(Object obj) 
{
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Point p = (Point) obj;

        return x == p.x && y == p.y;
    }
    public int hashCode() 
{
        return java.util.Objects.hash(x, y);
    }
}
