package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.lookups.model.FleetModel;


@Mapper
public interface FleetModelMapper {
    FleetModelMapper INSTANCE = Mappers.getMapper(FleetModelMapper.class);

    FleetModel toDomain(za.co.amakosifire.field.infrastructure.lookups.model.FleetModel model);

    za.co.amakosifire.field.infrastructure.lookups.model.FleetModel fromDomain(FleetModel model);
}
