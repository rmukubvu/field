package za.co.amakosifire.field.infrastructure.user.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    @Indexed
    private String roleId;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String contactNumber;
    private boolean isEnabled;
    private LocalDateTime creationDate;
}
