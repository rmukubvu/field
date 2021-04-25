package za.co.amakosifire.field.infrastructure.cache.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@RedisHash("DeviceUser")
public class DeviceUser implements Serializable {
    private String id;
    private String userId;
}
