package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.fleet.model.Fleet;
import za.co.amakosifire.field.domain.jobcard.model.JobCard;


@Mapper
public interface JobCardMapper {
    JobCardMapper INSTANCE = Mappers.getMapper(JobCardMapper.class);

    JobCard toDomain(za.co.amakosifire.field.infrastructure.jobcard.model.JobCard model);

    za.co.amakosifire.field.infrastructure.jobcard.model.JobCard fromDomain(JobCard model);
}
