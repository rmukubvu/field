package za.co.amakosifire.field.domain.review.model;

import lombok.Data;
import za.co.amakosifire.field.domain.shared.ModelOnSave;

import java.time.LocalDateTime;

@Data
public class Review implements ModelOnSave<Review> {
    private String id;
    private String userId;
    private int points;
    private String review;
    private LocalDateTime createdDate;

    @Override
    public Review onSave() {
        this.createdDate = LocalDateTime.now();
        return this;
    }
}
