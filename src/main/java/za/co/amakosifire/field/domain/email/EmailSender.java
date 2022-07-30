package za.co.amakosifire.field.domain.email;

public interface EmailSender {
    void send(String to, String subject, String email);
}
