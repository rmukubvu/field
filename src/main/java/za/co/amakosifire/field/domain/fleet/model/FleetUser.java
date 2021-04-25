package za.co.amakosifire.field.domain.fleet.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FleetUser {
    private String id;
    private String fleetId;
    private String userId;
    private LocalDateTime createdDate;
}
