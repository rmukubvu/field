package za.co.amakosifire.field.infrastructure.review;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.review.model.Review;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review,String> {
    List<Review> findAllByUserIdEquals(String userId);
}
