package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.service.model.ServiceAuction;

import java.util.Date;

@Mapper(imports = {Date.class} , uses = {AssetMapper.class})
public interface ServiceAuctionMapper {
    ServiceAuctionMapper INSTANCE =  Mappers.getMapper(ServiceAuctionMapper.class);

    ServiceAuction toDomain(za.co.amakosifire.field.infrastructure.service.model.ServiceAuction model);

    @Mapping(target = "createdDate" , defaultExpression = "java(new Date())")
    za.co.amakosifire.field.infrastructure.service.model.ServiceAuction fromDomain(ServiceAuction model);
}
