package za.co.amakosifire.field.domain.callout.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.geo.Point;
import za.co.amakosifire.field.domain.clients.model.Site;
import za.co.amakosifire.field.domain.image.model.Photo;
import za.co.amakosifire.field.domain.auth.model.User;

import java.time.LocalDateTime;

@Data
@Builder
public class Callout {
    private String id;
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
