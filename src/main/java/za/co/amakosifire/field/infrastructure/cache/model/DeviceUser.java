package za.co.amakosifire.field.infrastructure.cache.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
public class DeviceUser implements Serializable {
    private String id;
    private String deviceId;
}
