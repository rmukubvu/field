package za.co.amakosifire.field.infrastructure.site.configuration;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.site.configuration.model.SiteConfiguration;

import java.util.List;


public interface SiteConfigurationRepository extends MongoRepository<SiteConfiguration,String> {
    List<SiteConfiguration> findBySiteIdEquals(String siteId);
}
