package za.co.amakosifire.field.infrastructure.asset;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.co.amakosifire.field.infrastructure.asset.model.Asset;

public interface AssetRepository extends MongoRepository<Asset,String> {
}
