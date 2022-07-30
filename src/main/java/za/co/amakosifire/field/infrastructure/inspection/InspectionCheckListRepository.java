package za.co.amakosifire.field.infrastructure.inspection;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.inspection.model.InspectionCheckList;

public interface InspectionCheckListRepository extends MongoRepository<InspectionCheckList,String>  {
}
