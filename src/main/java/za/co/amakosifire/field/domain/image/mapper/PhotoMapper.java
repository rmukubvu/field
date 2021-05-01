package za.co.amakosifire.field.domain.image.mapper;

import org.mapstruct.Mapper;
import za.co.amakosifire.field.domain.image.model.Photo;

import static org.mapstruct.factory.Mappers.getMapper;


@Mapper
public interface PhotoMapper {

    PhotoMapper INSTANCE = getMapper(PhotoMapper.class);

    Photo toDomain(za.co.amakosifire.field.infrastructure.user.model.Photo user);

    za.co.amakosifire.field.infrastructure.user.model.Photo fromDomain(Photo user);
}
