package za.co.amakosifire.field.infrastructure.lookups.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "fleet_models")
public class FleetModel {
    @Id
    private String id;
    private String model;
    @Indexed(unique = true, dropDups = true)
    private String make;
}
