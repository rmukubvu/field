package za.co.amakosifire.field.infrastructure.inspection;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.inspection.model.Inspection;

public interface InspectionRepository extends MongoRepository<Inspection,String>  {
}
