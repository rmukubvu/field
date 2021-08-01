package za.co.amakosifire.field.domain.site.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class SiteConfiguration {
    private String id;
    private String siteId;
    private String pumpId;
    private Date createdDate;
}
