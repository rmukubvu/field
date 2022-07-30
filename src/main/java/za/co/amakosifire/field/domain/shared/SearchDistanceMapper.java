package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.lookups.model.SearchDistance;


@Mapper
public interface SearchDistanceMapper {
    SearchDistanceMapper INSTANCE = Mappers.getMapper(SearchDistanceMapper.class);

    SearchDistance toDomain(za.co.amakosifire.field.infrastructure.lookups.model.SearchDistance model);

    za.co.amakosifire.field.infrastructure.lookups.model.SearchDistance fromDomain(SearchDistance model);
}
