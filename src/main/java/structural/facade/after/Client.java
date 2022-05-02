package structural.facade.after;

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
