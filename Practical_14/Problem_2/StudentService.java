package service;
import model.Student;
import java.util.ArrayList;
import java.io.*;
public class StudentService 
{
    private ArrayList<Student> students = new ArrayList<>();
    public void addStudent(Student student) 
    {
        students.add(student);
    }
    public int getCount() 
    {
        return students.size();
    }
    public ArrayList<Student> getStudents() 
    {
        return new ArrayList<>(students);
    }
    public void saveData(String filename) throws IOException 
    {
        ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(students);
        out.close();
    }
    @SuppressWarnings("unchecked")
    public void loadData(String filename)
            throws IOException, ClassNotFoundException 
    {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        students = (ArrayList<Student>) in.readObject();
        in.close();
    }
}