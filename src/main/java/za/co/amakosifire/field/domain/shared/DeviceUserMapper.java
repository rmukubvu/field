package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.cache.model.DeviceUser;

@Mapper
public interface DeviceUserMapper {
    DeviceUserMapper INSTANCE = Mappers.getMapper(DeviceUserMapper.class);

    za.co.amakosifire.field.infrastructure.cache.model.DeviceUser fromDomain(DeviceUser model);

    DeviceUser toDomain(za.co.amakosifire.field.infrastructure.cache.model.DeviceUser model);

}
