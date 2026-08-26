public class Fraction 
{
    private int num;
    private int den;
    public Fraction(int num, int den) 
{
        int g = gcd(num, den);
        this.num = num / g;
        this.den = den / g;
    }
    private int gcd(int a, int b) 
{
        while (b != 0) 
{
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public String toString() 
{
        return num + "/" + den;
    }
    public boolean equals(Object obj) 
{
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Fraction f = (Fraction) obj;
        return num == f.num && den == f.den;
    }
    public int hashCode() {

        return java.util.Objects.hash(num, den);
    }
}
