package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.clients.model.Site;

@Mapper
public interface SiteMapper {
    SiteMapper INSTANCE = Mappers.getMapper(SiteMapper.class);

    Site toDomain(za.co.amakosifire.field.infrastructure.clients.model.Site model);

    za.co.amakosifire.field.infrastructure.clients.model.Site fromDomain(Site model);
}
