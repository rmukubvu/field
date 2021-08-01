package za.co.amakosifire.field.domain.fleet.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class FleetUser {
    private String id;
    private String fleetId;
    private String userId;
    private Date createdDate;
}
