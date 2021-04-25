package za.co.amakosifire.field.domain.callout.model;

import lombok.Data;
import org.springframework.data.geo.Point;


import java.time.LocalDateTime;

@Data
public class AcceptedCallout {
    private String id;
    private String userId;
    private String siteId;
    private Point point;
    private LocalDateTime acceptedDate;
}
