package za.co.amakosifire.field.infrastructure.review.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Data
public class Review {
    @Id
    private String id;
    @Indexed
    private String userId;
    private int points;
    private String review;
    private LocalDateTime createdDate;
}
