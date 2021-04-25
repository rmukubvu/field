package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.fleet.model.Fleet;
import za.co.amakosifire.field.domain.fleet.model.FleetUser;


@Mapper
public interface FleetUserMapper {
    FleetUserMapper INSTANCE = Mappers.getMapper(FleetUserMapper.class);

    FleetUser toDomain(za.co.amakosifire.field.infrastructure.fleet.model.FleetUser model);

    za.co.amakosifire.field.infrastructure.fleet.model.FleetUser fromDomain(FleetUser model);
}
