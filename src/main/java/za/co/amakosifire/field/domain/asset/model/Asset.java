package za.co.amakosifire.field.domain.asset.model;

import lombok.Data;
import org.springframework.data.geo.Point;

import java.util.Date;

@Data
public class Asset {
    private String id;
    private String assetTypeId;
    private String name;
    private String serialNumber;
    private Integer serviceInterval;
    private Point point;
    private Date creationDate;
}
