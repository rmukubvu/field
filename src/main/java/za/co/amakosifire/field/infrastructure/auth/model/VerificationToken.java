package za.co.amakosifire.field.infrastructure.auth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import za.co.amakosifire.field.domain.auth.model.User;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "token")
public class VerificationToken {
    @Id
    private String id;
    @Indexed
    private String token;
    private User user;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date expiryDate;
}
