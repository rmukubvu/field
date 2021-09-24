package za.co.amakosifire.field.infrastructure.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.service.model.ServiceAuction;

import java.util.Collection;

public interface ServiceAuctionRepository extends MongoRepository<ServiceAuction,String> {
    Collection<ServiceAuction> findAllByAllocatedEquals(Boolean allocated);
}
