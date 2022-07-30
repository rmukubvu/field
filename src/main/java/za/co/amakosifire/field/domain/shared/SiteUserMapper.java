package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.clients.model.Site;
import za.co.amakosifire.field.domain.site.model.SiteUser;

@Mapper
public interface SiteUserMapper {
    SiteUserMapper INSTANCE = Mappers.getMapper(SiteUserMapper.class);

    SiteUser toDomain(za.co.amakosifire.field.infrastructure.site.configuration.model.SiteUser model);

    za.co.amakosifire.field.infrastructure.site.configuration.model.SiteUser fromDomain(SiteUser model);
}
