package za.co.amakosifire.field.domain.service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Maintenance {
    private String id;
    private String assetId;
    private int nextServiceYear;
    private int nextServiceMonth;
    private int nextServiceDay;
    private Boolean thirtyDayNotificationTouched;
    private Date nextServiceDate;
    private Date statusChangeDate;
    private Date creationDate;
}
