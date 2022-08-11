package za.co.amakosifire.field.infrastructure.clients.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "sites")
public class Site {
    @Id
    private String id;
    @Indexed
    private String clientId;
    private String name;
    private String contactPerson;
    private String contactNumber;
    private String address;
    private String city;
    private String province;
    private Coordinates point;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date creationDate;
}
