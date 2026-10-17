package service;
import model.Student;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;
public class StudentServiceTest 
{
    @Test
    public void testSaveAndLoadData() throws Exception 
    {
        Path file = Files.createTempFile("students", ".dat");
        try 
        {
            StudentService service = new StudentService();
            service.addStudent(new Student(1, "Aman"));
            service.addStudent(new Student(2, "Riya"));
            service.saveData(file.toString());
            StudentService newService = new StudentService();
            newService.loadData(file.toString());
            assertEquals(service.getStudents(),newService.getStudents());
        } 
        finally
        {
            Files.deleteIfExists(file);
        }
    }
}