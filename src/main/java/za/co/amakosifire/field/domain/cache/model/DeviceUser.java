package za.co.amakosifire.field.domain.cache.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceUser {
    private String id;
    private String deviceId;
}
