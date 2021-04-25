package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.fleet.model.Fleet;


@Mapper
public interface FleetMapper {
    FleetMapper INSTANCE = Mappers.getMapper(FleetMapper.class);

    Fleet toDomain(za.co.amakosifire.field.infrastructure.fleet.model.Fleet model);

    za.co.amakosifire.field.infrastructure.fleet.model.Fleet fromDomain(Fleet model);
}
