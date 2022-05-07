package behavioral.chainofresponsibility.apply;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 체인 필터를 설정하는 코드
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
        //http.authorizeRequests().anyRequest().permitAll().and().addFilter(new MyFilter());
    }
}
