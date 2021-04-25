package za.co.amakosifire.field.domain.clients.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import za.co.amakosifire.field.domain.shared.ModelOnSave;

import java.time.LocalDateTime;

@Data
public class Site implements ModelOnSave<Site> {
    private String id;
    private String clientId;
    private String name;
    private String contactPerson;
    private String contactNumber;
    private String address;
    private String city;
    private String province;
    private Point point;
    private LocalDateTime creationDate;

    @Override
    public Site onSave() {
        this.creationDate = LocalDateTime.now();
        return this;
    }
}
