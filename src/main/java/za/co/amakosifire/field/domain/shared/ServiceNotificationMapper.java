package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.service.model.ServiceAuction;
import za.co.amakosifire.field.domain.service.model.ServiceNotification;

import java.util.Date;

@Mapper(imports = {Date.class})
public interface ServiceNotificationMapper {
    ServiceNotificationMapper INSTANCE =  Mappers.getMapper(ServiceNotificationMapper.class);

    ServiceNotification toDomain(za.co.amakosifire.field.infrastructure.service.model.ServiceNotification model);

    @Mapping(target = "createdDate" , defaultExpression = "java(new Date())")
    za.co.amakosifire.field.infrastructure.service.model.ServiceNotification fromDomain(ServiceNotification model);
}
