package za.co.amakosifire.field.infrastructure.pumps;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.pumps.model.Pump;


public interface PumpRepository extends MongoRepository<Pump,String> {
}
