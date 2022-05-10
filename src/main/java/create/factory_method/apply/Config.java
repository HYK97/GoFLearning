package create.factory_method.apply;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public String hello() {
        return "java hello";
    }
}
