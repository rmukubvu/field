package za.co.amakosifire.field.infrastructure.service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@Document(collection = "service_notification")
public class ServiceNotification {
    @Id
    private String id;
    @Indexed
    private String assetId;
    private NotificationType notificationType;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate;
}
