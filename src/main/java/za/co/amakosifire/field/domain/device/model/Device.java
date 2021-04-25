package za.co.amakosifire.field.domain.device.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import za.co.amakosifire.field.domain.shared.ModelOnSave;

import java.time.LocalDateTime;

@Data
public class Device implements ModelOnSave<Device> {
    private String id;
    private String userId;
    private boolean isEnabled;
    private LocalDateTime creationDate;

    @Override
    public Device onSave() {
        this.creationDate = LocalDateTime.now();
        return this;
    }
}
