package za.co.amakosifire.field.infrastructure.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.service.model.ServiceNotification;

public interface ServiceNotificationRepository extends MongoRepository<ServiceNotification,String> {
}
