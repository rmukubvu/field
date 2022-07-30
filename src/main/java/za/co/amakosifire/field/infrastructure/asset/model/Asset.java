package za.co.amakosifire.field.infrastructure.asset.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Document(collection = "asset")
public class Asset {
    @Id
    private String id;
    private String assetTypeId;
    private String name;
    @Indexed
    private String serialNumber;
    private Integer serviceInterval;
    private Point point;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date creationDate;
}
