package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.location.model.Location;
import za.co.amakosifire.field.domain.lookups.model.City;

@Mapper(uses = {GeoMapper.class})
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    za.co.amakosifire.field.infrastructure.location.model.Location fromDomain(Location model);

    Location toDomain(za.co.amakosifire.field.infrastructure.location.model.Location model);
}
