package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.site.model.Pump;

@Mapper
public interface PumpMapper {
    PumpMapper INSTANCE = Mappers.getMapper(PumpMapper.class);

    Pump toDomain(za.co.amakosifire.field.infrastructure.pumps.model.Pump model);

    za.co.amakosifire.field.infrastructure.pumps.model.Pump fromDomain(Pump model);
}
