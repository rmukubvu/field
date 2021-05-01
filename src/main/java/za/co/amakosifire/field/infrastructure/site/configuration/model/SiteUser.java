package za.co.amakosifire.field.infrastructure.site.configuration.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "site_user")
public class SiteUser {
    @Id
    private String id;
    @Indexed
    private String userId;
    private String siteId;
}
