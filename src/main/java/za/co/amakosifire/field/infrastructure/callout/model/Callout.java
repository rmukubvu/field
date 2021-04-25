package za.co.amakosifire.field.infrastructure.callout.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import za.co.amakosifire.field.domain.clients.model.Site;
import za.co.amakosifire.field.domain.user.model.Photo;
import za.co.amakosifire.field.domain.user.model.User;

import java.time.LocalDateTime;

@Data
@Document(collection = "callout")
public class Callout {
    @Id
    private String id;
    @Indexed
    private String referenceNumber;
    private Site site;
    private Point calloutPoint;
    private String rating;
    private String car;
    private Photo photo;
    private User user;
    private String callOutDistance;
    private LocalDateTime acceptedDate;
}
