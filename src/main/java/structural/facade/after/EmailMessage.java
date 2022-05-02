package structural.facade.after;

import lombok.Data;

@Data
public class EmailMessage {

    private String from;

    private String to;

    private String subject;

    private String text;
}
