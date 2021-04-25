package za.co.amakosifire.field.infrastructure.lookups;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.lookups.model.City;
import za.co.amakosifire.field.infrastructure.lookups.model.FleetModel;

import java.util.List;

public interface FleetModelRepository extends MongoRepository<FleetModel,String> {
    List<FleetModel> findAllByMakeEquals(String make);
}
