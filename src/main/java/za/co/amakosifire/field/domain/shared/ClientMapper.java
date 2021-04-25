package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.clients.model.Client;


@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client toDomain(za.co.amakosifire.field.infrastructure.clients.model.Client model);

    za.co.amakosifire.field.infrastructure.clients.model.Client fromDomain(Client model);
}
