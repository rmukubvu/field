package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.asset.model.Asset;
import za.co.amakosifire.field.domain.service.model.ServiceAuction;

@Mapper
public interface AssetMapper {
    AssetMapper INSTANCE =  Mappers.getMapper(AssetMapper.class);

    Asset toDomain(za.co.amakosifire.field.infrastructure.asset.model.Asset model);

    za.co.amakosifire.field.infrastructure.asset.model.Asset fromDomain(Asset model);
}
