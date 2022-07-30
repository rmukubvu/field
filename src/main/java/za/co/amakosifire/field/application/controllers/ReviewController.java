package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.amakosifire.field.domain.review.ReviewService;
import za.co.amakosifire.field.domain.review.model.Review;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/review")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> forUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(reviewService.reviewsFor(userId));
    }

    @PostMapping
    public ResponseEntity<?> postAReview(@Validated @RequestBody Review model) throws URISyntaxException {
        var result = reviewService.addReview(model);
        return ResponseEntity.created(new URI("/api/v1/review/" + result.getUserId())).body(result);
    }
}
