package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.site.model.SiteConfiguration;
import za.co.amakosifire.field.domain.site.model.SiteUser;

@Mapper
public interface SiteConfigurationMapper {
    SiteConfigurationMapper INSTANCE = Mappers.getMapper(SiteConfigurationMapper.class);

    SiteConfiguration toDomain(za.co.amakosifire.field.infrastructure.site.configuration.model.SiteConfiguration model);

    za.co.amakosifire.field.infrastructure.site.configuration.model.SiteConfiguration fromDomain(SiteConfiguration model);
}
