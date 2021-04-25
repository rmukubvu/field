package za.co.amakosifire.field.domain.user.mapper;

import org.mapstruct.Mapper;
import za.co.amakosifire.field.infrastructure.user.model.User;

import static org.mapstruct.factory.Mappers.getMapper;


@Mapper
public interface UserMapper {

    UserMapper INSTANCE = getMapper(UserMapper.class);

    User fromDomain(za.co.amakosifire.field.domain.user.model.User user);

    za.co.amakosifire.field.domain.user.model.User toDomain(User user);
}
