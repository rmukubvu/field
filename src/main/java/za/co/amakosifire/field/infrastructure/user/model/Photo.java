package za.co.amakosifire.field.infrastructure.user.model;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "photos")
public class Photo {
    @Id
    private String id;
    @Indexed
    private String userId;
    private String title;
    private Binary image;
    private LocalDateTime creationDate;
}
