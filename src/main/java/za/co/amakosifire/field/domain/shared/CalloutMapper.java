package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.callout.model.Callout;


@Mapper
public interface CalloutMapper {
    CalloutMapper INSTANCE = Mappers.getMapper(CalloutMapper.class);

    Callout toDomain(za.co.amakosifire.field.infrastructure.callout.model.Callout model);

    za.co.amakosifire.field.infrastructure.callout.model.Callout fromDomain(Callout model);
}
