package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.service.model.ServiceAuction;
import za.co.amakosifire.field.domain.service.model.ServiceHistory;

import java.util.Date;

@Mapper(imports = {Date.class})
public interface ServiceHistoryMapper {
    ServiceHistoryMapper INSTANCE = Mappers.getMapper(ServiceHistoryMapper.class);

    ServiceHistory toDomain(za.co.amakosifire.field.infrastructure.service.model.ServiceHistory model);

    @Mapping(target = "createdDate", defaultExpression = "java(new Date())")
    za.co.amakosifire.field.infrastructure.service.model.ServiceHistory fromDomain(ServiceHistory model);
}
