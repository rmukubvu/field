package za.co.amakosifire.field.domain.notifications.model;

import lombok.Data;

@Data
public class DeviceMessage {
    private String deviceId;
    private String message;
}
