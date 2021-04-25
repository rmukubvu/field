package za.co.amakosifire.field.infrastructure.jobcard.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class JobCard {
    @Id
    private String id;
    private String reference;
    private LocalDateTime creationDate;
}
