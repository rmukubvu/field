package za.co.amakosifire.field.domain.callout.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.geo.Point;
import za.co.amakosifire.field.domain.clients.model.Site;
import za.co.amakosifire.field.domain.image.model.Photo;
import za.co.amakosifire.field.domain.auth.model.User;
import za.co.amakosifire.field.domain.location.model.Coordinates;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class Callout {
    private String id;
    private String referenceNumber;
    private Site site;
    private Coordinates calloutPoint;
    private String rating;
    private String car;
    private Photo photo;
    private User user;
    private String callOutDistance;
    private Date acceptedDate;
}
