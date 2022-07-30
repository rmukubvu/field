package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.infrastructure.cache.model.LastKnownLocation;

@Mapper
public interface LastKnownLocationMapper {
    LastKnownLocationMapper INSTANCE = Mappers.getMapper(LastKnownLocationMapper.class);

    LastKnownLocation fromDomain(za.co.amakosifire.field.domain.location.model.LastKnownLocation model);
}
