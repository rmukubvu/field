package za.co.amakosifire.field.infrastructure.callout.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import za.co.amakosifire.field.domain.clients.model.Site;
import za.co.amakosifire.field.domain.image.model.Photo;
import za.co.amakosifire.field.domain.auth.model.User;
import za.co.amakosifire.field.infrastructure.clients.model.Coordinates;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "callout")
public class Callout {
    @Id
    private String id;
    @Indexed
    private String referenceNumber;
    private Site site;
    private Coordinates calloutPoint;
    private String rating;
    private String car;
    private Photo photo;
    private User user;
    private String callOutDistance;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date acceptedDate;

}
