package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.lookups.model.City;
import za.co.amakosifire.field.domain.lookups.model.PumpType;

@Mapper
public interface PumpTypeMapper {
    PumpTypeMapper INSTANCE = Mappers.getMapper(PumpTypeMapper.class);

    PumpType toDomain(za.co.amakosifire.field.infrastructure.lookups.model.PumpType model);

    za.co.amakosifire.field.infrastructure.lookups.model.PumpType fromDomain(PumpType model);
}
