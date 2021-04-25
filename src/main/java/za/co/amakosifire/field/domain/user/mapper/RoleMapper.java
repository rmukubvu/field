package za.co.amakosifire.field.domain.user.mapper;

import org.mapstruct.Mapper;
import za.co.amakosifire.field.domain.user.model.Role;

import static org.mapstruct.factory.Mappers.getMapper;


@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = getMapper(RoleMapper.class);

    Role toDomain(za.co.amakosifire.field.infrastructure.user.model.Role role);

    za.co.amakosifire.field.infrastructure.user.model.Role fromDomain(Role role);
}
