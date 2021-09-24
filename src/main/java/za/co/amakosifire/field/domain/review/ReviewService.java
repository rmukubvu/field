package za.co.amakosifire.field.domain.review;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.amakosifire.field.domain.review.model.Review;
import za.co.amakosifire.field.domain.review.model.dto.ReviewDto;
import za.co.amakosifire.field.domain.shared.Rating;
import za.co.amakosifire.field.domain.shared.ReviewMapper;
import za.co.amakosifire.field.infrastructure.review.ReviewRepository;

import java.text.DecimalFormat;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;


    public Review addReview(Review review) {
        return ReviewMapper.INSTANCE.toDomain(
                reviewRepository.save(ReviewMapper.INSTANCE.fromDomain(review.onSave())));
    }

    public String ratingFrom(String userId) {
        return new DecimalFormat("0.0").format(ratingFor(userId));
    }

    public ReviewDto reviewsFor(String userId) {
        var reviews = reviewRepository.findAllByUserIdEquals(userId);
        return ReviewDto.builder()
                .count(reviews.size())
                .rating(ratingFrom(userId))
                .reviews(reviews.stream().map(ReviewMapper.INSTANCE::toDomain).collect(Collectors.toList()))
                .build();
    }

    private double ratingFor(String userId) {
        var rating = new Rating();
        var reviews = reviewRepository.findAllByUserIdEquals(userId);
        reviews.stream().forEach(review -> rating.add(ReviewMapper.INSTANCE.toDomain(review)));
        return rating.points;
    }
}
