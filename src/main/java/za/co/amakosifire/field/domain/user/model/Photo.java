package za.co.amakosifire.field.domain.user.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.Binary;

import java.time.LocalDateTime;

@Data
@Builder
public class Photo {
    private String id;
    private String userId;
    private String title;
    private Binary image;
    private LocalDateTime creationDate;
}
