package za.co.amakosifire.field.infrastructure.lookups.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "distance_constant")
public class SearchDistance {
    @Id
    private String id;
    private Integer minimumRadius;
    private Integer maximumRadius;
    private Integer lastFanOutRadius;
}
