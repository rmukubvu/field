package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.lookups.model.City;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    City toDomain(za.co.amakosifire.field.infrastructure.lookups.model.City city);

    za.co.amakosifire.field.infrastructure.lookups.model.City fromDomain(City city);
}
