package za.co.amakosifire.field.infrastructure.clients;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.clients.model.Site;

import java.util.Collection;

public interface SiteRepository extends MongoRepository<Site,String> {
    Collection<Site> findSitesByClientIdEquals(String clientId);
}
