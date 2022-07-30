package za.co.amakosifire.field.domain.shared;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import za.co.amakosifire.field.domain.review.model.Review;


@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Review toDomain(za.co.amakosifire.field.infrastructure.review.model.Review model);

    za.co.amakosifire.field.infrastructure.review.model.Review fromDomain(Review model);
}
