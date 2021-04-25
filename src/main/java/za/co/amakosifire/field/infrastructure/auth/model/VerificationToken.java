package za.co.amakosifire.field.infrastructure.auth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import za.co.amakosifire.field.domain.user.model.User;

import java.time.LocalDateTime;

@Data
@Document(collection = "token")
public class VerificationToken {
    @Id
    private String id;
    @Indexed
    private String token;
    private User user;
    private LocalDateTime expiryDate;
}
