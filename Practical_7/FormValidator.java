import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {
    @NotBlank
    String name;

    @NotBlank
    @MaxLength(10)
    String username;

    @NotBlank
    String email;

    SignupForm(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }
}

public class FormValidator {
    public static List<String> check(Object obj) {
        List<String> errors = new ArrayList<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                String value = (String) field.get(obj);

                if (field.isAnnotationPresent(NotBlank.class)) {
                    if (value == null || value.trim().isEmpty())
                        errors.add(field.getName() + " cannot be blank");
                }

                if (field.isAnnotationPresent(MaxLength.class)) {
                    int max = field.getAnnotation(MaxLength.class).value();

                    if (value != null && value.length() > max)
                        errors.add(field.getName() + " is too long");
                }
            } catch (Exception e) {
                errors.add("Error checking " + field.getName());
            }
        }
        return errors;
    }

    public static void main(String[] args) {
        SignupForm form = new SignupForm("", "verylongusername123", "abc@gmail.com");

        List<String> errors = check(form);

        for (String error : errors)
            System.out.println(error);
    }
}