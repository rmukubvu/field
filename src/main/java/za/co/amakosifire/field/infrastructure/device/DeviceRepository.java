package za.co.amakosifire.field.infrastructure.device;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.device.model.Device;

public interface DeviceRepository extends MongoRepository<Device,String> {
    Device findByUserIdEquals(String userId);
}
