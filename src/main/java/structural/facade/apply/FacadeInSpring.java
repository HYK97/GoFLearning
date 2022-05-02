package structural.facade.apply;

import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;

public class FacadeInSpring {

    public static void main(String[] args) {

        /**
         * MailSender 퍼싸드 인터페이스 특정 기술에 종속적이지 않음
         * 뒤로 JavaMailSenderImpl을 이용해 숨긴것 것
         * 실질적으로 사용하는것은 JavaMailSenderImpl
         * */
        MailSender mailSender = new JavaMailSenderImpl();


        /**
         * PlatformTransactionManager 퍼싸드 인터페이스 특정 기술에 종속적이지
         * 뒤로 JdbcTransactionManager 이용해 숨긴것 것
         * 실질적으로 사용하는것은 JdbcTransactionManager
         * */
        PlatformTransactionManager platformTransactionManager = new JdbcTransactionManager();
    }
}
