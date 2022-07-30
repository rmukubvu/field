package za.co.amakosifire.field.domain.jobcard.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class JobCard {
    private String id;
    private String reference;
    private Date creationDate;
}
