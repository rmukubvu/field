package za.co.amakosifire.field.domain.callout.model;

import lombok.Data;
import za.co.amakosifire.field.domain.location.model.Coordinates;


import java.time.LocalDateTime;

@Data
public class AcceptedCallout {
    private String id;
    private String userId;
    private String siteId;
    private Coordinates point;
    private LocalDateTime acceptedDate;
}
