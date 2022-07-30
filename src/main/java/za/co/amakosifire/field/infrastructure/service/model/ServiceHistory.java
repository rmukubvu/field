package za.co.amakosifire.field.infrastructure.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Document(collection = "service_history")
public class ServiceHistory {
    @Id
    private String id;
    @Indexed
    private String assetId;
    //user id who serviced the asset
    private String servicedBy;
    //notify when due for service
    private Boolean serviceNotificationSent;
    private String comment;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date previousServiceDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date nextServiceDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate;
}
