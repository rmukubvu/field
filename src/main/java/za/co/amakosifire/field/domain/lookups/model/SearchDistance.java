package za.co.amakosifire.field.domain.lookups.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class SearchDistance {
    private String id;
    private Integer minimumRadius;
    private Integer maximumRadius;
    private Integer lastFanOutRadius;
}
