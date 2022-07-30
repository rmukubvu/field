package za.co.amakosifire.field.domain.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ServiceHistory {
    private String id;
    private String assetId;
    private String servicedBy;
    private Boolean serviceNotificationSent;
    private String comment;
    private Date previousServiceDate;
    private Date nextServiceDate;
    private Date createdDate;
}
