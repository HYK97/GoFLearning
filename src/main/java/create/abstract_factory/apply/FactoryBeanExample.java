package create.abstract_factory.apply;

import create.factory_method.after.Ship;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanExample {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        Ship whiteShip = applicationContext.getBean("whiteShip", Ship.class);
        System.out.println("whiteShip = " + whiteShip.getName());

    }
}
