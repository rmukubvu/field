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
@Document(collection = "service")
public class Maintenance {
    @Id
    private String id;
    @Indexed
    private String assetId;
    private int nextServiceYear;
    private int nextServiceMonth;
    private int nextServiceDay;
    private Boolean thirtyDayNotificationTouched;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date nextServiceDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date statusChangeDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date creationDate;
}
