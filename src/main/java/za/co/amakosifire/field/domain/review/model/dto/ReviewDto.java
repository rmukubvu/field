package za.co.amakosifire.field.domain.review.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReviewDto {
    private int count;
    private String rating;
    private List<za.co.amakosifire.field.domain.review.model.Review> reviews;
}
