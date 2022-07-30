package za.co.amakosifire.field.domain.service.model;

import lombok.Builder;
import lombok.Data;
import za.co.amakosifire.field.domain.asset.model.Asset;

import java.util.Date;

@Data
@Builder
public class ServiceAuction {
    private String id;
    private Asset asset;
    private Boolean allocated;
    private String allocatedTo;
    private Date allocatedDate;
    private Date createdDate;
}
