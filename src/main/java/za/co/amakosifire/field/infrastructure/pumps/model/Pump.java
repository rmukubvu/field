package za.co.amakosifire.field.infrastructure.pumps.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "pump")
public class Pump {
    @Id
    private String id;
    @Indexed
    private String typeId;
    @Indexed
    private String serialNumber;
    private String name;
}
