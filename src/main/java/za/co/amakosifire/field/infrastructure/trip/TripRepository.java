package za.co.amakosifire.field.infrastructure.trip;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.trip.model.Trip;

public interface TripRepository extends MongoRepository<Trip,String>  {
}
