package za.co.amakosifire.field.infrastructure.service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import za.co.amakosifire.field.infrastructure.asset.model.Asset;

import java.util.Date;

@Data
@Builder
@Document(collection = "service_auction")
public class ServiceAuction {
    @Id
    private String id;
    private Asset asset;
    @Indexed
    private Boolean allocated;
    //user id of person allocated
    private String allocatedTo;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date allocatedDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate;
}
