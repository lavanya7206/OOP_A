import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class StudentServiceTest 
{
    @Test
    void testAddStudent() 
    {
        StudentService service = new StudentService();
        service.addStudent("Aman");
        assertEquals(1, service.getCount());
    }
    @Test
    void testAddingTwoStudents() 
    {
        StudentService service = new StudentService();
        service.addStudent("Aman");
        service.addStudent("Riya");
        assertEquals(2, service.getCount());
    }
    @Test
    void testInvalidName() 
    {
        StudentService service = new StudentService();
        assertThrows(IllegalArgumentException.class,
                () -> service.addStudent(""));
    }
}