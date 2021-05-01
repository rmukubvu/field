package za.co.amakosifire.field.domain.auth.mapper;

import org.mapstruct.Mapper;
import za.co.amakosifire.field.domain.auth.model.Role;

import static org.mapstruct.factory.Mappers.getMapper;


@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = getMapper(RoleMapper.class);

    Role toDomain(za.co.amakosifire.field.infrastructure.user.model.Role role);

    za.co.amakosifire.field.infrastructure.user.model.Role fromDomain(Role role);
}
