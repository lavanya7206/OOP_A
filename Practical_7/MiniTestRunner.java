import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {}

class MyTests {

    @Run
    public void testOne() {
        System.out.println("Test One executed");
    }

    @Run
    public void testTwo() {
        System.out.println("Test Two executed");
    }

    public void normalMethod() {
        System.out.println("Normal method");
    }
}

public class MiniTestRunner {
    public static void main(String[] args) throws Exception {

        MyTests obj = new MyTests();
        int count = 0;

        for (Method method : MyTests.class.getDeclaredMethods()) {

            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {

                method.invoke(obj);
                count++;
            }
        }

        System.out.println("Tests run: " + count);
    }
}