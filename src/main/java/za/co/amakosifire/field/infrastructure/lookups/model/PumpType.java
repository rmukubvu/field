package za.co.amakosifire.field.infrastructure.lookups.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "pump_type")
public class PumpType {
    @Id
    private String id;
    private String name;
}
