package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.device.model.Device;


@Mapper
public interface DeviceMapper {
    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    Device toDomain(za.co.amakosifire.field.infrastructure.device.model.Device model);

    za.co.amakosifire.field.infrastructure.device.model.Device fromDomain(Device model);
}
