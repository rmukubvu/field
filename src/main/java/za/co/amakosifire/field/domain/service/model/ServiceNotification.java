package za.co.amakosifire.field.domain.service.model;

import lombok.Data;
import java.util.Date;

@Data
public class ServiceNotification {
    private String id;
    private String assetId;
    private NotificationType notificationType;
    private Date createdDate;
}
