package za.co.amakosifire.field.infrastructure.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.amakosifire.field.infrastructure.cache.model.DeviceUser;
import za.co.amakosifire.field.infrastructure.cache.model.LastKnownLocation;

@Repository
public interface LastKnownLocationRepository extends CrudRepository<LastKnownLocation,String> {
}
