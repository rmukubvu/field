package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.auth.model.RefreshToken;

@Mapper
public interface RefreshTokenMapper {
    RefreshTokenMapper INSTANCE = Mappers.getMapper(RefreshTokenMapper.class);

    za.co.amakosifire.field.infrastructure.auth.model.RefreshToken fromDomain(RefreshToken model);

    RefreshToken toDomain(za.co.amakosifire.field.infrastructure.auth.model.RefreshToken model);
}
