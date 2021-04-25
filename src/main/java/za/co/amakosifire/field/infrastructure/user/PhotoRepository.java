package za.co.amakosifire.field.infrastructure.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.user.model.Photo;

import java.util.Optional;

public interface PhotoRepository extends MongoRepository<Photo, String> {
    Optional<Photo> findPhotoByUserIdEquals(String userId);
}
