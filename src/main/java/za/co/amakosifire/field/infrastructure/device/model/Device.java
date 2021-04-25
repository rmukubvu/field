package za.co.amakosifire.field.infrastructure.device.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "devices")
public class Device {
    @Id
    private String id;
    @Indexed
    private String userId;
    private boolean isEnabled;
    private LocalDateTime creationDate;
}
