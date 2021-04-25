package za.co.amakosifire.field.infrastructure.auth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "refresh_token")
public class RefreshToken {
    @Id
    private String id;
    @Indexed
    private String token;
    private LocalDateTime createdDate;
}
