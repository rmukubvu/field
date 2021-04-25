package za.co.amakosifire.field.domain.user.mapper;

import org.mapstruct.Mapper;
import za.co.amakosifire.field.domain.user.model.Photo;

import static org.mapstruct.factory.Mappers.getMapper;


@Mapper
public interface PhotoMapper {

    PhotoMapper INSTANCE = getMapper(PhotoMapper.class);

    Photo toDomain(za.co.amakosifire.field.infrastructure.user.model.Photo user);

    za.co.amakosifire.field.infrastructure.user.model.Photo fromDomain(Photo user);
}
