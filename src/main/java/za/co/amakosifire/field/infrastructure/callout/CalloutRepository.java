package za.co.amakosifire.field.infrastructure.callout;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.callout.model.Callout;


public interface CalloutRepository extends MongoRepository<Callout,String> {
    Callout findByReferenceNumberEquals(String referenceNumber);
}
