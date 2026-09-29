import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import model.annotation.*;

public class AnnotationValidator {

    public static String[] validate(Object obj) {

        List<String> errors = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);

            try {
                Object value = field.get(obj);

                if (field.isAnnotationPresent(Positive.class)) {
                    Positive p = field.getAnnotation(Positive.class);

                    if (value instanceof Number &&
                        ((Number) value).longValue() <= 0) {

                        errors.add(field.getName() + " " + p.message());
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {
                    MaxLength m = field.getAnnotation(MaxLength.class);

                    if (value instanceof String &&
                        ((String) value).length() > m.value()) {

                        errors.add(field.getName() +
                                " must have maximum " + m.value() + " characters");
                    }
                }

            } catch (IllegalAccessException e) {
                errors.add("Cannot access field: " + field.getName());
            }
        }

        return errors.toArray(new String[0]);
    }
}