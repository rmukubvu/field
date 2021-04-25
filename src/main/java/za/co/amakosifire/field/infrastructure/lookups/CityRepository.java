package za.co.amakosifire.field.infrastructure.lookups;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.lookups.model.City;
import za.co.amakosifire.field.infrastructure.lookups.model.Province;

public interface CityRepository extends MongoRepository<City,String> {
}
