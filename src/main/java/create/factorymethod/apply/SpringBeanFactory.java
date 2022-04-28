package create.factorymethod.apply;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanFactory {

    public static void main(String[] args) {
        /**
         * BeanFactory -> creator
         * String hello -> product
         * */
        BeanFactory javaFactory = new AnnotationConfigApplicationContext(Config.class);
        String hello = javaFactory.getBean("hello", String.class);
        System.out.println("Annotation hello = " + hello);

        BeanFactory xmlFactory = new ClassPathXmlApplicationContext("context.xml");
        String hello1 = xmlFactory.getBean("hello", String.class);
        System.out.println("xmlFactory hello = " + hello1);

    }
}
