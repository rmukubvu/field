package za.co.amakosifire.field.domain.location.model;

import lombok.Builder;
import lombok.Data;
import za.co.amakosifire.field.domain.shared.ModelOnSave;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Builder
public class Location implements ModelOnSave<Location> {
    private String id;
    private String deviceId;
    private Coordinates point;
    private Date creationDate;

    @Override
    public Location onSave() {
        this.creationDate = new Date();
        return this;
    }
}
