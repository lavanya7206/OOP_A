import java.io.*;
class Student implements Serializable
{
    int rollNo;
    String name;
    transient String password;
    Student(int rollNo, String name, String password)
    {
        this.rollNo = rollNo;
        this.name = name;
        this.password = password;
    }
}
public class SaveLoad
{
    public static void main(String[] args)
    {
        try
        {
            Student[] s1 =
            {
                new Student(1, "Aman", "abc123"),
                new Student(2, "Riya", "xyz456")
            };
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.txt"));
            out.writeObject(s1);
            out.close();
            System.out.println("Objects saved successfully.");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.txt"));
            Student[] s2 = (Student[]) in.readObject();
            in.close();
            for (Student s : s2) 
            {
                System.out.println("Roll No: " + s.rollNo);
                System.out.println("Name: " + s.name);
                System.out.println("Password: " + s.password);
                System.out.println();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
