package main;
import model.Student;
import service.StudentService;
public class Main 
{
    public static void main(String[] args) throws Exception 
    {
        StudentService service = new StudentService();
        service.addStudent(new Student(1, "Aman"));
        service.addStudent(new Student(2, "Riya"));
        service.saveData("students.dat");
        System.out.println("Data saved successfully.");
        StudentService newService = new StudentService();
        newService.loadData("students.dat");
        System.out.println("Loaded student records:");
        for (Student s : newService.getStudents()) 
        {
            System.out.println(s);
        }
    }
}