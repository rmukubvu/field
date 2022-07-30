package za.co.amakosifire.field.infrastructure.jobcard;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.jobcard.model.JobCard;

public interface JobCardRepository extends MongoRepository<JobCard,String> {
}
