package za.co.amakosifire.field.domain.shared;

import za.co.amakosifire.field.domain.review.model.Review;

import java.util.ArrayList;
import java.util.List;

public class Rating {

    public double points;
    List<Review> reviews = new ArrayList<>();

    public void add(Review review) {
        reviews.add(review);
        computeRating();
    }

    private double computeRating() {
        double totalPoints =
                reviews.stream().map(Review::getPoints).reduce(0, Integer::sum);
        this.points = totalPoints / reviews.size();
        return this.points;
    }

}
