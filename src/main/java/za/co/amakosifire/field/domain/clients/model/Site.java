package za.co.amakosifire.field.domain.clients.model;

import lombok.Data;
import za.co.amakosifire.field.domain.location.model.Coordinates;
import za.co.amakosifire.field.domain.shared.ModelOnSave;

import java.util.Date;

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
    private Coordinates point;
    private Date creationDate;

    @Override
    public Site onSave() {
        this.creationDate = new Date();
        return this;
    }
}
