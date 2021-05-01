package za.co.amakosifire.field.infrastructure.site.configuration.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "site_configuration")
public class SiteConfiguration {
    @Id
    private String id;
    @Indexed
    private String siteId;
    private String pumpId;
    private LocalDateTime createdDate;
}
