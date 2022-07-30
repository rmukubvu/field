package za.co.amakosifire.field.infrastructure.fleet;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.fleet.model.Fleet;


public interface FleetRepository extends MongoRepository<Fleet,String> {
}
