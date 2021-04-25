package za.co.amakosifire.field.domain.inspection.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class InspectionCheckList {
    private String id;
    private String name;
    private String value;
    private LocalDateTime creationDate;
}

/* mimic strapi */
