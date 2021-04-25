package za.co.amakosifire.field.infrastructure.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.amakosifire.field.infrastructure.cache.model.DeviceUser;

@Repository
public interface DeviceUserRepository extends CrudRepository<DeviceUser,String> {
}
