package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.fleet.model.Trip;


@Mapper
public interface TripMapper {
    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

    Trip toDomain(za.co.amakosifire.field.infrastructure.trip.model.Trip model);

    za.co.amakosifire.field.infrastructure.trip.model.Trip fromDomain(Trip model);
}
