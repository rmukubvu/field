package za.co.amakosifire.field.infrastructure.clients.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

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
    private Point point;
    private LocalDateTime creationDate;
}
