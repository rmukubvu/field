package za.co.amakosifire.field.infrastructure.user;


import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.user.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findUserByUserNameEquals(String userName);
}
