package create.singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringExample {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        String hello = applicationContext.getBean("hello", String.class);
        String hello2 = applicationContext.getBean("hello", String.class);
        System.out.println("hello2.hashCode() = " + hello2.hashCode());
        System.out.println("hello.hashCode() = " + hello.hashCode());
        System.out.println(hello == hello2);
    }
}
