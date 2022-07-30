package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.lookups.model.City;
import za.co.amakosifire.field.domain.lookups.model.Province;

@Mapper
public interface ProvinceMapper {
    ProvinceMapper INSTANCE = Mappers.getMapper(ProvinceMapper.class);

    Province toDomain(za.co.amakosifire.field.infrastructure.lookups.model.Province model);

    za.co.amakosifire.field.infrastructure.lookups.model.Province fromDomain(Province model);
}
