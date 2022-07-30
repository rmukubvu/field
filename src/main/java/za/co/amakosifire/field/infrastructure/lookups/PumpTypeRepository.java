package za.co.amakosifire.field.infrastructure.lookups;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.lookups.model.PumpType;

public interface PumpTypeRepository extends MongoRepository<PumpType,String> {
}
