package za.co.amakosifire.field.infrastructure.trip.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "trip")
public class Trip {
    @Id
    private String id;
    @Indexed
    private String fleetId;
    @Indexed
    private String userId;
    private String startDate;
    private String endDate;
    private LocalDateTime createdDate;
    private String createdBy;
}
