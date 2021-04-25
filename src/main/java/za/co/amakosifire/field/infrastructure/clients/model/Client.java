package za.co.amakosifire.field.infrastructure.clients.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "client")
public class Client {
    @Id
    private String id;
    private String name;
    @Indexed
    private String accountNumber;
    private String address;
    private LocalDateTime creationDate;
}
