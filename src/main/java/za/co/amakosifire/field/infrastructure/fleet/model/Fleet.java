package za.co.amakosifire.field.infrastructure.fleet.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "fleet")
public class Fleet {
    @Id
    private String id;
    @Indexed
    private String fleetModelId;
    private String registrationNumber;
    private String fleetNumber;
    private String color;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date creationDate;
}
