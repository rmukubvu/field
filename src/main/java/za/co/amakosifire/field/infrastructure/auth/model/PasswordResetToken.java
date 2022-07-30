package za.co.amakosifire.field.infrastructure.auth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import za.co.amakosifire.field.domain.auth.model.User;

import java.util.Date;

@Data
@Document(collection = "pwd_reset_token")
public class PasswordResetToken {
    @Id
    private String id;
    @Indexed
    private String token;
    private User user;
    private Boolean changed;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date expiredAt;
}
