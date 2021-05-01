package za.co.amakosifire.field.domain.site.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SiteConfiguration {
    private String id;
    private String siteId;
    private String pumpId;
    private LocalDateTime createdDate;
}
