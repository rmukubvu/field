package za.co.amakosifire.field.domain.device.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import za.co.amakosifire.field.domain.shared.ModelOnSave;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Device implements ModelOnSave<Device> {
    private String id;
    private String userId;
    private boolean isEnabled;
    private Date creationDate;

    @Override
    public Device onSave() {
        this.creationDate = new Date();
        return this;
    }
}
