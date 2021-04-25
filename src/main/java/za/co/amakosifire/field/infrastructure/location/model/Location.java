package za.co.amakosifire.field.infrastructure.location.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "device_location")
public class Location {
    @Id
    private String id;
    @Indexed
    private String deviceId;
    @GeoSpatialIndexed(type= GeoSpatialIndexType.GEO_2DSPHERE)
    private Point location;
    private LocalDateTime creationDate;
}
