package za.co.amakosifire.field.infrastructure.user;


import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.user.model.Role;

public interface RoleRepository extends MongoRepository<Role,String> {
}
