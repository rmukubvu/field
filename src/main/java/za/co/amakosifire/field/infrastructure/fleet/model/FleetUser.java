package za.co.amakosifire.field.infrastructure.fleet.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "fleet_user")
public class FleetUser {
    @Id
    private String id;
    @Indexed
    private String fleetId;
    private String userId;
    private LocalDateTime createdDate;
}
