package model;
import java.io.Serializable;
public class Student implements Serializable 
{
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    public Student(int id, String name) 
    {
        this.id = id;
        this.name = name;
    }
    public int getId() 
    {
        return id;
    }
    public String getName() 
    {
        return name;
    }
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return id == other.id && name.equals(other.name);
    }
    @Override
    public String toString() 
    {
        return id + " - " + name;
    }
}
