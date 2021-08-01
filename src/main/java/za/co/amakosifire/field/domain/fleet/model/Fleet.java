package za.co.amakosifire.field.domain.fleet.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import za.co.amakosifire.field.domain.shared.ModelOnSave;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Fleet implements ModelOnSave<Fleet> {
    private String id;
    private String fleetModelId;
    private String registrationNumber;
    private String fleetNumber;
    private String color;
    private Date creationDate;

    @Override
    public Fleet onSave() {
        this.creationDate = new Date();
        return this;
    }
}
