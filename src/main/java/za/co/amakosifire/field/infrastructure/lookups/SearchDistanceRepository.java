package za.co.amakosifire.field.infrastructure.lookups;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.lookups.model.SearchDistance;

public interface SearchDistanceRepository extends MongoRepository<SearchDistance,String> {
}
