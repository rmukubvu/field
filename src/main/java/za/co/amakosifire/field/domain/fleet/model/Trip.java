package za.co.amakosifire.field.domain.fleet.model;

import lombok.Data;
import za.co.amakosifire.field.domain.shared.ModelOnSave;

import java.time.LocalDateTime;

@Data
public class Trip implements ModelOnSave<Trip> {
    private String id;
    private String fleetId;
    private String userId;
    private String startDate;
    private String endDate;
    private LocalDateTime createdDate;
    private String createdBy;

    @Override
    public Trip onSave() {
        this.createdDate = LocalDateTime.now();
        return this;
    }
}
