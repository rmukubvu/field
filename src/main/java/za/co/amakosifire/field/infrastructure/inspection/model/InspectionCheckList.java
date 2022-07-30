package za.co.amakosifire.field.infrastructure.inspection.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "check_list")
public class InspectionCheckList {
    @Id
    private String id;
    private String name;
    private String value;
    private String comment;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date creationDate;
}
