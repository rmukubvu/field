package za.co.amakosifire.field.domain.cache.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class DeviceUser {
    private String id;
    private String deviceId;
}
