package za.co.amakosifire.field.domain.asset.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class AssetType {
    private String id;
    private String name;
}
