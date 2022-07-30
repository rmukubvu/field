package za.co.amakosifire.field.infrastructure.fleet;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.fleet.model.FleetUser;

public interface FleetUserRepository extends MongoRepository<FleetUser,String> {
    FleetUser findByFleetIdEquals(String fleetId);
}
