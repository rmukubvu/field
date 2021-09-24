package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.service.model.Maintenance;

@Mapper
public interface MaintenanceMapper {
    MaintenanceMapper INSTANCE =  Mappers.getMapper(MaintenanceMapper.class);

    Maintenance toDomain(za.co.amakosifire.field.infrastructure.service.model.Maintenance service);

    @Mapping(target = "creationDate" , defaultExpression = "java(new java.util.Date())")
    za.co.amakosifire.field.infrastructure.service.model.Maintenance fromDomain(Maintenance service);
}
