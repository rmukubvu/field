package za.co.amakosifire.field.infrastructure.inspection.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "check_list")
public class InspectionCheckList {
    @Id
    private String id;
    private String name;
    private String value;
    private LocalDateTime creationDate;
}
