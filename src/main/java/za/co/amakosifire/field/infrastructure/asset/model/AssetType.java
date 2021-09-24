package za.co.amakosifire.field.infrastructure.asset.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "asset_type")
public class AssetType {
    @Id
    private String id;
    private String name;
}
