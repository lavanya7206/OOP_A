import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {
    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "age")
    String age;

    public String toString() {
        return name + " | " + email + " | " + age;
    }
}

public class ColumnMapper {

    public static void main(String[] args) throws Exception {

        String[] header = {"name", "email", "age"};
        String[] data = {"Lavanya", "lavanya@gmail.com", "20"};

        Student student = new Student();

        for (Field field : Student.class.getDeclaredFields()) {

            if (field.isAnnotationPresent(Column.class)) {

                String columnName =
                    field.getAnnotation(Column.class).name();

                for (int i = 0; i < header.length; i++) {

                    if (header[i].equals(columnName)) {
                        field.setAccessible(true);
                        field.set(student, data[i]);
                    }
                }
            }
        }

        System.out.println(student);
    }
}
