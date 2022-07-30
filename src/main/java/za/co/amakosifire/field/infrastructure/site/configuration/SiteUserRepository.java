package za.co.amakosifire.field.infrastructure.site.configuration;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.site.configuration.model.SiteUser;

import java.util.List;


public interface SiteUserRepository extends MongoRepository<SiteUser,String> {
    List<SiteUser> findByUserIdEquals(String userId);
}
