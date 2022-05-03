# Facade 패턴이란

#### Facade라는 단어는 프랑스말로 건물의 정면을 의미하는 단어이다. 즉 자신이 숨기고자하는 코드를 간략화하고 클라이언트는 그 간략화된 코드를 사용하도록 하여 복잡한 코드를 뒤로 숨기는 패턴이다. 즉 클라이언트가 사용하는 코드는 의존적이지않게 하고 퍼사드 뒤에 숨겨진 코드가 대신 의존을 해주고 사용자는 간단한 인터페이스를 제공받아 사용한다.


## Facade 장단점

- 장점 :
  서브 시스템에 대한 의존성을 한곳으로 모을 수 있다.
  사용자입장에서 간단하고 쉽게 사용가능하다.




- 단점 :
  퍼사드 클래스가 서브 시스템에 대한 모든 의존성을 가지게 된다.





## Facade 클래스 다이어그램
클라이언트는 Component를 사용해서 실제 객체를 사용하게 된다.

![](https://velog.velcdn.com/images/ddh963963/post/305807f8-e509-4ca4-9188-fd8e1dd6afd7/image.png)


> 출처 : https://m.blog.naver.com/lhm0812/221062416291



예시

- 메일 전송 시스템을 Facade로 구현 했을때 복잡한 라이브러리 의존성을 뒤로 숨길 수 있다.
  ![](https://velog.velcdn.com/images/ddh963963/post/6a7cbbd8-a567-4854-a760-0ab6d9ade2cf/image.jpg)




## Facade 패턴 요소
- Facade : client의 요청을 Subsystem에 전달하는 단순하고 일관된 통합 인터페이스

- Subsystem : Facade에 대한 정보를 가지지 않는 서브시스템


## Facade 구현하기

### EmailSender(Facade InterFace)


```java 

public interface EmailSender {

    public void sendEmail(EmailMessage emailMessage);
    
}


```


### DefaultEmailSender(Facade 구현체)


```java 

public class DefaultEmailSender implements EmailSender {

    private EmailSettings emailSettings;

    public DefaultEmailSender(EmailSettings emailSettings) {
        this.emailSettings = emailSettings;
    }

    @Override
    public void sendEmail(EmailMessage emailMessage) {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", emailSettings.getHost());
		//Session,Properties,MimeMessage,Transport 등 client가 가지고 있던 의존성을 Facade가 대신한다.
        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailMessage.getFrom()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailMessage.getTo()));
            message.setSubject(emailMessage.getSubject());
            message.setText(emailMessage.getText());

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}



```

### Client

- 적용전

```java
//client의 의존 객체들
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Client {

    public static void main(String[] args) {
        String to = "kkk@naver.com";
        String from = "jjj@naver.com";
        String host = "127.0.0.1";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Test");
            message.setText("message");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
```

- 적용후
- Client는 라이브러리에 의존하지않고 메일을 전송할 수 있다.
```java 

public class Client {

    public static void main(String[] args) {
    
        EmailSettings emailSettings = new EmailSettings();
        emailSettings.setHost("123.0.0.1");
        EmailMessage emailMessage = new EmailMessage();
        EmailSender EmailSender = new DefaultEmailSender(emailSettings);
        emailMessage.setFrom("Hy");
        emailMessage.setTo("kk");
        emailMessage.setSubject("제목");
        emailMessage.setText("텍스트");
        EmailSender.sendEmail(emailMessage);

    }
}


```




## Facade 어떻게 사용될까?



```java

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


```

