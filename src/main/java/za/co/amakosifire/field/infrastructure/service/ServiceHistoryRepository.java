package za.co.amakosifire.field.infrastructure.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.service.model.ServiceHistory;

import java.util.Collection;

public interface ServiceHistoryRepository extends MongoRepository<ServiceHistory,String> {
    Collection<ServiceHistory> findAllByAssetIdEquals(String assetId);
}
