package za.co.amakosifire.field.infrastructure.location;

import org.springframework.data.geo.Distance;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.location.model.Location;

import java.util.List;

public interface LocationRepository extends MongoRepository<Location,String> {
    List<Location> findByLocationNear(Point point, Distance maxDistance);
}
